package club.example.oauth2.server.endpoint;

import club.example.oauth2.server.entity.OAuthClientDetail;
import club.example.oauth2.server.service.OAuth2ClientDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/client/detail")
public class OAuth2ClientDetailsController {

    private final OAuth2ClientDetailsService oAuth2ClientDetailsService;

    @Autowired
    public OAuth2ClientDetailsController(OAuth2ClientDetailsService oAuth2ClientDetailsService) {
        this.oAuth2ClientDetailsService = oAuth2ClientDetailsService;
    }

    @GetMapping
    public String getClientDetails(
            @RequestParam(value = "clientId", required = false, defaultValue = "") String clientId,
            @RequestParam(value = "resourceId", required = false, defaultValue = "") String resourceId,
            @RequestParam(value = "scope", required = false, defaultValue = "") String scope, Model model) {
        log.info("getClientDetails clientId = {}, resourceId = {}, scope = {}", clientId, resourceId, scope);
        List<OAuthClientDetail> clientDetails = oAuth2ClientDetailsService.getAll(clientId, resourceId, scope);
        model.addAttribute("clientDetails", clientDetails);
        // return new JsonResponse<>(0, "success", clientDetails);
        return "clients";
    }

    @GetMapping("/{clientId}")
    public String getClientDetail(@PathVariable("clientId") String clientId, Model model) {
        log.info("getClientDetail clientId = {}", clientId);
        OAuthClientDetail oAuthClientDetail = oAuth2ClientDetailsService.get(clientId);
        model.addAttribute("oAuthClient", oAuthClientDetail);
        return "client";
    }
}
