package club.example.oauth2.server.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.security")
public class OAuth2SecurityProperties {

    private String storeType = "jwt";

    private JWTSecurityProperties jwt = new JWTSecurityProperties();

    private AuthorizationCodeProperties authorizationCode = new AuthorizationCodeProperties();

    private BrowserLoginProperties browser = new BrowserLoginProperties();

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

    public BrowserLoginProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserLoginProperties browser) {
        this.browser = browser;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }
}
