package club.example.oauth2.appservice.endpoint;

import club.example.oauth2.appservice.entity.ServiceVersion;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/service/version")
public class ServiceVersionController {

    @GetMapping
    public ServiceVersion getVersion() {
        ServiceVersion serviceVersion = new ServiceVersion();
        serviceVersion.setName("VersionNamed");
        return serviceVersion;
    }

}
