package club.example.oauth2.server.service.impl;

import club.example.oauth2.server.entity.OAuthClientDetail;
import club.example.oauth2.server.param.OAuth2ClientDetailCreateModel;
import club.example.oauth2.server.repository.OAuthClientDetailMapper;
import club.example.oauth2.server.service.OAuth2ClientDetailsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return null;
    }
}
