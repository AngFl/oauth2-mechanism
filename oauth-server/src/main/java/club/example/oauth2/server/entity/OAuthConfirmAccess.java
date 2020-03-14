package club.example.oauth2.server.entity;

public class OAuthConfirmAccess {

    private String confirmAccessUrl;

    private String clientId;

    private String csrfParameterName;

    private String csrfToken;

    public String getConfirmAccessUrl() {
        return confirmAccessUrl;
    }

    public void setConfirmAccessUrl(String confirmAccessUrl) {
        this.confirmAccessUrl = confirmAccessUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCsrfParameterName() {
        return csrfParameterName;
    }

    public void setCsrfParameterName(String csrfParameterName) {
        this.csrfParameterName = csrfParameterName;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }
}
