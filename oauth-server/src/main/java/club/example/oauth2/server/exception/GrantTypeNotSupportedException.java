package club.example.oauth2.server.exception;

public class GrantTypeNotSupportedException extends RuntimeException {
    public GrantTypeNotSupportedException(String message) {
        super(message);
    }
}
