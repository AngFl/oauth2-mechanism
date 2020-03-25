package club.example.oauth2.server.security.granter;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;

public class MobileCodeTokenGranter implements TokenGranter {


    @Override
    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
        return null;
    }
}
