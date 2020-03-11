package club.example.oauth2.server.service;

import club.example.oauth2.server.entity.OAuthClientDetail;
import club.example.oauth2.server.repository.OAuthClientDetailMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@Primary
public class MybatisOAuthClientsDetailService implements ClientDetailsService {

    private final OAuthClientDetailMapper oAuthClientDetailMapper;

    @Autowired
    public MybatisOAuthClientsDetailService(OAuthClientDetailMapper oAuthClientDetailMapper) {
        this.oAuthClientDetailMapper = oAuthClientDetailMapper;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.info("loadClientByClientId clientId = {}", clientId);
        LambdaQueryWrapper<OAuthClientDetail> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper
                .eq(OAuthClientDetail::getMetaFlag, 0)
                .eq(OAuthClientDetail::getClientId, clientId);
        OAuthClientDetail authClientDetail = oAuthClientDetailMapper.selectOne(lambdaQueryWrapper);
        if (null == authClientDetail) {
            throw new ClientRegistrationException("no such clientId:" + clientId);
        }

        BaseClientDetails baseClientDetails = new BaseClientDetails();
        BeanUtils.copyProperties(authClientDetail, baseClientDetails);

        String detailGrantTypes = authClientDetail.getGrantTypes();
        if (StringUtils.hasText(detailGrantTypes)) {
            baseClientDetails.setAuthorizedGrantTypes(
                    Arrays.asList(detailGrantTypes.split(",")));
        }

        String detailScopes = authClientDetail.getScopes();
        if (StringUtils.hasText(detailScopes)) {
            baseClientDetails.setScope(Arrays.asList(detailScopes.split(",")));
        }

        String redirectUri = authClientDetail.getRedirectUri();
        if (StringUtils.hasText(redirectUri)) {
            Set<String> stringSet = Arrays.stream(redirectUri.split(","))
                    .collect(Collectors.toSet());
            baseClientDetails.setRegisteredRedirectUri(stringSet);
        }
        baseClientDetails.isAutoApprove(String.valueOf(authClientDetail.getAutoApprove().isApprove()));
        baseClientDetails.setAuthorities(Collections.emptyList());
        log.info("load clientDetail = {}", baseClientDetails);
        //自动批准作用于，授权码模式时使用，登录验证后直接返回code，不再需要下一步点击授权
        return baseClientDetails;
    }
}
