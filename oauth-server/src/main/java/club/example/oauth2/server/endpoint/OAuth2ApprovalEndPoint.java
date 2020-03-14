package club.example.oauth2.server.endpoint;

import club.example.oauth2.server.entity.OAuthConfirmAccess;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Controller for displaying the approval page for the authorization server.
 *
 * @author Dave Syer
 */
@RestController
@SessionAttributes("authorizationRequest")
public class OAuth2ApprovalEndPoint {

    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        OAuthConfirmAccess authConfirmAccess = new OAuthConfirmAccess();
        authConfirmAccess = createTemplate(model, request, authConfirmAccess);
        if (request.getAttribute("_csrf") != null) {
            authConfirmAccess.setCsrfParameterName("_csrf");
            authConfirmAccess.setCsrfToken(String.valueOf(request.getAttribute("_csrf")));
        }

        return new ModelAndView("confirm_access", "oauthConfirm", authConfirmAccess);
    }

    protected OAuthConfirmAccess createTemplate(Map<String, Object> model, HttpServletRequest request, OAuthConfirmAccess confirmAccess) {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        String clientId = authorizationRequest.getClientId();

        String requestPath = ServletUriComponentsBuilder.fromContextPath(request).build().getPath();
        if (requestPath == null) {
            requestPath = "";
        }

        confirmAccess.setClientId(clientId);
        confirmAccess.setConfirmAccessUrl(requestPath + "/oauth/authorize");
        return confirmAccess;
    }
}