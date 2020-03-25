package club.example.oauth2.server.exception;

import org.springframework.security.core.AuthenticationException;

public class OAuthMobileCodeGrantBadCredentialsException extends AuthenticationException {
    public OAuthMobileCodeGrantBadCredentialsException(String msg) {
        super(msg);
    }
}
