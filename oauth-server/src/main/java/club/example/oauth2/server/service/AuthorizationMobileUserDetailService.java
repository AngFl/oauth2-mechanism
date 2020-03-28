package club.example.oauth2.server.service;

import club.example.oauth2.server.constant.OAuth2GrantCacheKey;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service("authorizationMobileUserDetailService")
public class AuthorizationMobileUserDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final StringRedisTemplate stringRedisTemplate;

    public AuthorizationMobileUserDetailService(PasswordEncoder passwordEncoder,
                                                StringRedisTemplate stringRedisTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        log.info("loadUserByUsername mobileNumber = {}", mobileNumber);
        String code = stringRedisTemplate.opsForValue()
                .get(OAuth2GrantCacheKey.MOBILE_CODE_GRANT_CACHE_KEY + mobileNumber);
        if (null == code || code.isEmpty()) {
            throw new UsernameNotFoundException("客户端手机登录信息不存在");
        }
        return User.withUsername(mobileNumber)
                .password(passwordEncoder.encode(code)).build();
    }
}
