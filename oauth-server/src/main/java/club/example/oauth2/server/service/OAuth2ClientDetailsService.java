package club.example.oauth2.server.service;

import club.example.oauth2.server.entity.OAuthClientDetail;
import club.example.oauth2.server.param.OAuth2ClientDetailCreateModel;

import java.util.List;

public interface OAuth2ClientDetailsService {

    /**
     * 获取所有 客户端信息列表
     * @param clientId String
     * @param resourceId String  资源服务器ID
     * @param scope 客户端作用域
     * @return List
     */
    List<OAuthClientDetail> getAll(String clientId, String resourceId, String scope);

    /**
     * 客户端信息数据
     * @param clientId String
     * @return  OAuthClientDetail
     */
    OAuthClientDetail get(String clientId);

    OAuthClientDetail create(OAuth2ClientDetailCreateModel model);

    OAuthClientDetail update(OAuth2ClientDetailCreateModel model);
}
