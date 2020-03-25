package club.example.oauth2.server.service;

public interface OAuth2MobileCodeGrantService {

    String sendCode(String mobileNumber);

    boolean validate(String mobile, String mobileCode);
}
