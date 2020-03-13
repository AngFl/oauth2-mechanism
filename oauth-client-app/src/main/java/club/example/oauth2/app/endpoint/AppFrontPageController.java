package club.example.oauth2.app.endpoint;

import club.example.oauth2.app.entity.JsonResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/app/front")
public class AppFrontPageController {

    @GetMapping
    public JsonResult<String> frontPage(@AuthenticationPrincipal String username) {
        log.info("frontPage username = {}", username);
        return new JsonResult<>(0, "success", username);
    }

    @PostMapping
    public JsonResult<OAuth2Authentication> forkPage(@AuthenticationPrincipal OAuth2Authentication auth2Authentication) {
        log.info("forkPage oauth2Authentication = {}", auth2Authentication.getUserAuthentication().getPrincipal());
        return new JsonResult<>(0, "success", auth2Authentication);
    }
}
