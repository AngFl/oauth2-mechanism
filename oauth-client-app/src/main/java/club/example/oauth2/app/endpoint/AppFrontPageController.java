package club.example.oauth2.app.endpoint;

import club.example.oauth2.app.entity.JsonResult;
import club.example.oauth2.app.entity.ServiceVersion;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/app/front")
public class AppFrontPageController {

    private final OAuth2RestTemplate restTemplate;

    @Autowired
    public AppFrontPageController(OAuth2RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping
    public JsonResult<String> frontPage(@AuthenticationPrincipal String username) {
        log.info("frontPage username = {}", username);
        return new JsonResult<>(0, "success", username);
    }

    @PostMapping
    public JsonResult<OAuth2Authentication> forkPage(@AuthenticationPrincipal OAuth2Authentication auth2Authentication) {
        log.info("forkPage oauth2Authentication = {}", auth2Authentication.getUserAuthentication().getPrincipal());
        ServiceVersion serviceVersion = restTemplate.getForObject(
                "http://localhost:8036/resource-service/service/version", ServiceVersion.class);
        assert serviceVersion != null;
        log.info("serviceVersion.version = {}", serviceVersion.getVersion());
        return new JsonResult<>(0, "success", auth2Authentication);
    }
}
