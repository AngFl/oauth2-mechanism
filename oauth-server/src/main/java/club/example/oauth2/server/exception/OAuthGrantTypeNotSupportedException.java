package club.example.oauth2.server.exception;

public class OAuthGrantTypeNotSupportedException extends RuntimeException {
    public OAuthGrantTypeNotSupportedException(String message) {
        super(message);
    }
}
