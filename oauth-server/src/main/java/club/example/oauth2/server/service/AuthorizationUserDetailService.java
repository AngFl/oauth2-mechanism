package club.example.oauth2.server.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service("authorizationUserDetailService")
public class AuthorizationUserDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthorizationUserDetailService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadByUsername username = {}", username);
        return User.withUsername(username).password(passwordEncoder.encode("asd123"))
                .authorities("ROLE_ADMIN").build();
    }
}
