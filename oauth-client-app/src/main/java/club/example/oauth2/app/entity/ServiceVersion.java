package club.example.oauth2.app.entity;

public class ServiceVersion {

    private String version = "v0.4-SNAPSHOT";

    private String name = "appService";

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }
}
