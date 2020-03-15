package club.example.oauth2.app.handler;

import club.example.oauth2.app.security.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

@Component
public class AppSecurityExpressionHandler extends OAuth2WebSecurityExpressionHandler {

    private final PermissionService permissionService;

    @Autowired
    public AppSecurityExpressionHandler(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    protected StandardEvaluationContext createEvaluationContextInternal(Authentication authentication, FilterInvocation invocation) {
        StandardEvaluationContext evaluationContext = super.createEvaluationContextInternal(authentication, invocation);
        evaluationContext.setVariable("permissionService", permissionService);
        return evaluationContext;
    }
}
