package club.example.oauth2.server.config.properties;

public class AuthorizationCodeProperties {

    private long expiration = 300L;

    private String prefix = "";

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
