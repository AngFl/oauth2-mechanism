package club.example.oauth2.server.service;

import club.example.oauth2.server.entity.extra.OAuth2AuthenticationUserDetail;
import club.example.oauth2.server.repository.AuthenticationUserDetailMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service("authorizationUserDetailService")
public class AuthorizationUserDetailService implements UserDetailsService {

    private final AuthenticationUserDetailMapper userDetailMapper;

    @Autowired
    public AuthorizationUserDetailService(AuthenticationUserDetailMapper userDetailMapper) {
        this.userDetailMapper = userDetailMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadByUsername username = {}", username);
        OAuth2AuthenticationUserDetail userDetail = userDetailMapper.findByUsername(username);
        if (null == userDetail) {
            // 非 UsernamePasswordAuthentication
            // 载入其他输数据附加表
            // 手机号码登录 //
            // 二维码登录
            throw new UsernameNotFoundException("username = " + username);
        }
        return userDetail;
    }
}
