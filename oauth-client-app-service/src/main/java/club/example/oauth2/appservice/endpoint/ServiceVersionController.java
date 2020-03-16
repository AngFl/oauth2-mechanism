package club.example.oauth2.appservice.endpoint;

import club.example.oauth2.appservice.entity.ServiceVersion;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/service/version")
public class ServiceVersionController {

    @GetMapping
    public ServiceVersion getVersion(@AuthenticationPrincipal OAuth2Authentication auth2Authentication) {
        log.info("getVersion auth2Authentication  = {}", auth2Authentication.getUserAuthentication().getPrincipal());
        ServiceVersion serviceVersion = new ServiceVersion();
        serviceVersion.setName("VersionNamed");
        return serviceVersion;
    }

}
