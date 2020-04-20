package club.example.oauth2.server;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OAuth2ServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OAuth2ServerApplication.class)
                .web(WebApplicationType.SERVLET)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
