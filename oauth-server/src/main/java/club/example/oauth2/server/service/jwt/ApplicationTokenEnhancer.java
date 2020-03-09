package club.example.oauth2.server.service.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;


public class ApplicationTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalMap = new HashMap<>();
        additionalMap.put("additional", "userCustomInfo");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalMap);
        return accessToken;
    }
}
