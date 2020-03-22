package club.example.oauth2.server.exception;

public class OAuthClientScopeNotSupportedException extends RuntimeException {
    public OAuthClientScopeNotSupportedException(String message) {
        super(message);
    }
}
