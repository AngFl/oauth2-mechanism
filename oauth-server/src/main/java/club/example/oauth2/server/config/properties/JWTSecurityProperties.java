package club.example.oauth2.server.config.properties;

public class JWTSecurityProperties {

    private String signKey;

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }
}
