package club.example.oauth2.admin.endpoint;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/client/admin")
public class ClientAdminController {

    @GetMapping
    public String getAdminUserPrinciple(@AuthenticationPrincipal Authentication user) {
        log.info("user = {}", user.getCredentials());
        return (String) user.getPrincipal();
    }
}
