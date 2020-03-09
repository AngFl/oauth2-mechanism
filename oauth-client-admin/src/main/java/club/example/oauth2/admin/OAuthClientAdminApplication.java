package club.example.oauth2.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@EnableOAuth2Sso
@SpringBootApplication
public class OAuthClientAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthClientAdminApplication.class, args);
    }
}
