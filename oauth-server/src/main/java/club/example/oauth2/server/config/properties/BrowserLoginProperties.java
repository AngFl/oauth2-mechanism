package club.example.oauth2.server.config.properties;

public class BrowserLoginProperties {

    private String loginPage = "/oauth-sign-in.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
