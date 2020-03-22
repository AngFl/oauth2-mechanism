package club.example.oauth2.server.service.impl;

import club.example.oauth2.server.constant.EnumOAuthClientScope;
import club.example.oauth2.server.constant.EnumOAuthGrantType;
import club.example.oauth2.server.entity.OAuthClientDetail;
import club.example.oauth2.server.exception.OAuthClientDetailNotFoundException;
import club.example.oauth2.server.exception.OAuthClientScopeNotSupportedException;
import club.example.oauth2.server.exception.OAuthGrantTypeNotSupportedException;
import club.example.oauth2.server.param.OAuth2ClientDetailCreateModel;
import club.example.oauth2.server.repository.OAuthClientDetailMapper;
import club.example.oauth2.server.service.OAuth2ClientDetailsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
public class OAuth2ClientDetailsServiceImpl implements OAuth2ClientDetailsService {

    private final OAuthClientDetailMapper oAuthClientDetailMapper;

    @Autowired
    public OAuth2ClientDetailsServiceImpl(OAuthClientDetailMapper oAuthClientDetailMapper) {
        this.oAuthClientDetailMapper = oAuthClientDetailMapper;
    }

    @Override
    public List<OAuthClientDetail> getAll(String clientId, String resourceId, String scope) {
        log.info("getAll clientId = {}, resourceId = {}, scope = {}", clientId, resourceId, scope);
        LambdaQueryWrapper<OAuthClientDetail> queryWrapper = Wrappers.lambdaQuery();
        if (null != clientId && !clientId.isEmpty()) {
            queryWrapper.eq(OAuthClientDetail::getClientId, clientId);
        }

        if (null != resourceId && !resourceId.isEmpty()) {
            queryWrapper.eq(OAuthClientDetail::getResourceIds, resourceId);
        }

        if (null != scope && !scope.isEmpty()) {
            queryWrapper.eq(OAuthClientDetail::getScopes, scope);
        }

        List<OAuthClientDetail> oAuthClientDetails = oAuthClientDetailMapper.selectList(queryWrapper);
        log.info("client.size = {}", null != oAuthClientDetails ? oAuthClientDetails.size() : 0);
        return oAuthClientDetails;
    }

    @Override
    public OAuthClientDetail get(String clientId) {
        log.info("get ClientId = {}", clientId);
        LambdaQueryWrapper<OAuthClientDetail> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper
                .eq(OAuthClientDetail::getClientId, clientId)
                .eq(OAuthClientDetail::getMetaFlag, 0);
        return oAuthClientDetailMapper.selectOne(queryWrapper);
    }

    @Override
    public OAuthClientDetail create(OAuth2ClientDetailCreateModel model) {
        return null;
    }

    @Override
    public OAuthClientDetail update(OAuth2ClientDetailCreateModel model) {
        log.info("update oauthClientModel = {}", model.toString());
        String clientId = model.getClientId();
        if (!StringUtils.hasText(clientId)) {
            throw new IllegalArgumentException("clientId为空");
        }

        OAuthClientDetail oAuthClientDetail = oAuthClientDetailMapper.selectById(clientId);
        if (null == oAuthClientDetail || oAuthClientDetail.getMetaFlag() != 0) {
            throw new OAuthClientDetailNotFoundException("clientId = " + clientId + "客户端信息不存在");
        }

        if (! oAuthClientDetail.getResourceIds().equals(model.getResourceIds())) {
            oAuthClientDetail.setResourceIds(model.getResourceIds());
        }

        String grantTypes = model.getGrantTypes();
        if (! StringUtils.hasText(grantTypes)) {
            throw new IllegalArgumentException("授权模式不合法");
        }

        EnumOAuthGrantType enumOAuthGrantType = EnumOAuthGrantType.ofType(grantTypes);
        if (null == enumOAuthGrantType) {
            throw new OAuthGrantTypeNotSupportedException("当前授权模式不支持");
        }

        if (! grantTypes.equals(oAuthClientDetail.getGrantTypes())) {
            oAuthClientDetail.setGrantTypes(grantTypes);
        }

        String redirectUri = model.getRedirectUri();
        // 如果是授权码模式，一定需要添加redirectUri
        if (enumOAuthGrantType == EnumOAuthGrantType.AUTHORIZATION_CODE
            || enumOAuthGrantType == EnumOAuthGrantType.AUTHORIZATION_CODE_REFRESH) {
            if (! StringUtils.hasText(redirectUri)) {
                throw new IllegalArgumentException("授权码回调地址必填");
            }
            // TODO 当前回调地址需要做正则表达式匹配校验
            if (! redirectUri.equals(oAuthClientDetail.getRedirectUri())) {
                oAuthClientDetail.setRedirectUri(redirectUri);
            }
        }

        String scopes = model.getScopes();
        EnumOAuthClientScope enumOAuthClientScope = EnumOAuthClientScope.ofScope(scopes);
        if (null == enumOAuthClientScope) {
            throw new OAuthClientScopeNotSupportedException("当前客户端作用域不支持");
        }

        if (! scopes.equals(oAuthClientDetail.getScopes())) {
            oAuthClientDetail.setScopes(scopes);
        }

        oAuthClientDetail.setUpdatedAt(LocalDateTime.now());
        oAuthClientDetailMapper.updateById(oAuthClientDetail);

        return oAuthClientDetail;
    }
}
