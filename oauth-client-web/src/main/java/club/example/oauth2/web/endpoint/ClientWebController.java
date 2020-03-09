package club.example.oauth2.web.endpoint;

import club.example.oauth2.web.entity.JsonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/client/web")
public class ClientWebController {

    @GetMapping("/user")
    public JsonResponse<Authentication> user(@AuthenticationPrincipal Authentication user) {
        log.info("user method");
        if (user != null) {
            return new JsonResponse<>(0, "success", user);
        }
        return new JsonResponse<>(-1, "authentication failed");
    }
}
