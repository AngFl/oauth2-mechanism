package club.example.oauth2.server.security.provider;

import club.example.oauth2.server.entity.OAuth2MobileCodeAuthentication;
import club.example.oauth2.server.exception.OAuthMobileCodeGrantBadCredentialsException;
import club.example.oauth2.server.exception.OAuthMobileCodeGrantException;
import club.example.oauth2.server.service.OAuth2MobileCodeGrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;

@Component
public class MobileCodeAuthenticationProvider implements AuthenticationProvider {

    private final OAuth2MobileCodeGrantService oAuth2MobileCodeGrantService;

    @Autowired
    public MobileCodeAuthenticationProvider(OAuth2MobileCodeGrantService oAuth2MobileCodeGrantService) {
        this.oAuth2MobileCodeGrantService = oAuth2MobileCodeGrantService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String mobileNumber = (String) authentication.getPrincipal();
        if (StringUtils.isEmpty(mobileNumber)) {
            throw new OAuthMobileCodeGrantException("手机号码为空");
        }
        String mobileCode = (String) authentication.getCredentials();
        if (StringUtils.isEmpty(mobileCode)) {
            throw new OAuthMobileCodeGrantException("手机号验证码为空");
        }

        boolean validated = oAuth2MobileCodeGrantService.validate(mobileNumber, mobileCode);
        if (! validated) {
            throw new OAuthMobileCodeGrantBadCredentialsException("手机验证码有误");
        }
        OAuth2MobileCodeAuthentication mobileCodeAuthentication
                = new OAuth2MobileCodeAuthentication(Collections.emptyList(),
                mobileNumber, mobileCode);
        mobileCodeAuthentication.setDetails("MobileCode");
        return mobileCodeAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OAuth2MobileCodeAuthentication.class
                .isAssignableFrom(authentication);
    }
}
