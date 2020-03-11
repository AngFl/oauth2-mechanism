package club.example.oauth2.server.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.security")
public class OAuth2SecurityProperties {

    private JWTSecurityProperties jwt = new JWTSecurityProperties();

    private AuthorizationCodeProperties authorizationCode = new AuthorizationCodeProperties();

    public JWTSecurityProperties getJwt() {
        return jwt;
    }

    public void setJwt(JWTSecurityProperties jwt) {
        this.jwt = jwt;
    }

    public AuthorizationCodeProperties getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(AuthorizationCodeProperties authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
}
