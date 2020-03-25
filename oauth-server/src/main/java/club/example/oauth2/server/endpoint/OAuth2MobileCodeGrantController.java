package club.example.oauth2.server.endpoint;

import club.example.oauth2.server.entity.web.JsonResponse;
import club.example.oauth2.server.service.OAuth2MobileCodeGrantService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/grant/mobile")
public class OAuth2MobileCodeGrantController {

    private final OAuth2MobileCodeGrantService mobileCodeGrantService;

    @Autowired
    public OAuth2MobileCodeGrantController(OAuth2MobileCodeGrantService mobileCodeGrantService) {
        this.mobileCodeGrantService = mobileCodeGrantService;
    }

    @GetMapping
    public JsonResponse<String> getGrantCode(@RequestParam("mobileNumber") String mobileNumber) {
        log.info("getGrantCode mobileNumber = {}", mobileNumber);
        String sendCode = mobileCodeGrantService.sendCode(mobileNumber);
        return new JsonResponse<>(0, "success", sendCode);
    }
}
