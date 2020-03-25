package club.example.oauth2.server.exception;

import org.springframework.security.core.AuthenticationException;

public class OAuthMobileCodeGrantException extends AuthenticationException {

    public OAuthMobileCodeGrantException(String msg) {
        super(msg);
    }
}
