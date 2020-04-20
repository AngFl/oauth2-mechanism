package club.example.oauth2.server.exception;

public class ClientDetailNotFoundException extends RuntimeException {

    public ClientDetailNotFoundException(String message) {
        super(message);
    }
}
