package club.example.oauth2.server.exception;

public class ClientScopeNotSupportedException extends RuntimeException {
    public ClientScopeNotSupportedException(String message) {
        super(message);
    }
}
