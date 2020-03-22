package club.example.oauth2.server.exception;

public class OAuthClientDetailNotFoundException extends RuntimeException {

    public OAuthClientDetailNotFoundException(String message) {
        super(message);
    }
}
