package club.example.oauth2.server.entity;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class OAuth2MobileCodeAuthentication extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;

    public OAuth2MobileCodeAuthentication(Object principal, Object credentials) {
        super(Collections.emptyList());
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public OAuth2MobileCodeAuthentication(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
