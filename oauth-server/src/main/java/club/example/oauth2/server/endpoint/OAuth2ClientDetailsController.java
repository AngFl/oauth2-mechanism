package club.example.oauth2.server.endpoint;

import club.example.oauth2.server.entity.OAuthClientDetail;
import club.example.oauth2.server.param.OAuth2ClientDetailCreateModel;
import club.example.oauth2.server.service.OAuth2ClientDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView getClientDetails(
            @RequestParam(value = "clientId", required = false, defaultValue = "") String clientId,
            @RequestParam(value = "resourceId", required = false, defaultValue = "") String resourceId,
            @RequestParam(value = "scope", required = false, defaultValue = "") String scope) {
        log.info("getClientDetails clientId = {}, resourceId = {}, scope = {}", clientId, resourceId, scope);
        List<OAuthClientDetail> clientDetails = oAuth2ClientDetailsService.getAll(clientId, resourceId, scope);
        return new ModelAndView("clients", "clientDetails", clientDetails);
    }

    @GetMapping("/{clientId}")
    public ModelAndView getClientDetail(@PathVariable("clientId") String clientId) {
        log.info("getClientDetail clientId = {}", clientId);
        OAuthClientDetail oAuthClientDetail = oAuth2ClientDetailsService.get(clientId);
        return new ModelAndView("client", "oAuthClient", oAuthClientDetail);
    }

    @PostMapping("/update")
    public ModelAndView updateClientDetail(@ModelAttribute OAuth2ClientDetailCreateModel model) {
        log.info("updateClientDetail model = {}", model.toString());
        oAuth2ClientDetailsService.update(model);
        List<OAuthClientDetail> detailList = oAuth2ClientDetailsService.getAll("", "", "");
        return new ModelAndView("clients", "clientDetails", detailList);
    }

    @PostMapping
    public ModelAndView createClientDetail(@ModelAttribute OAuth2ClientDetailCreateModel model) {
        log.info("createClientDetail model = {}", model);

        return new ModelAndView("");
    }
}
