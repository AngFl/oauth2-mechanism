package club.example.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final TokenStore tokenStore;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    private final TokenEnhancer jwtTokenEnhancer;

    @Autowired
    public OAuth2AuthorizationServerConfig(AuthenticationManager authenticationManager,
                                           PasswordEncoder passwordEncoder,
                                           @Qualifier("authorizationUserDetailService") UserDetailsService userDetailsService,
                                           @Qualifier("jwtTokenStore") TokenStore tokenStore,
                                           JwtAccessTokenConverter jwtAccessTokenConverter,
                                           TokenEnhancer jwtTokenEnhancer) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.tokenStore = tokenStore;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()")
                // TODO: 接受token的使用方 需要用 key 来验证签名，需要对外暴露服务来获取签名
                .tokenKeyAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore);
        // Token 增强配置
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(
                jwtAccessTokenConverter,
                jwtTokenEnhancer));

        endpoints.accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(enhancerChain);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("auth-client-a")
                    .secret(passwordEncoder.encode("123456"))
                    // 发放的令牌可以访问哪些资源服务器
                    .resourceIds("resourceApp-b")
                    // 令牌访问的有效期
                    .accessTokenValiditySeconds(7200)
                    .refreshTokenValiditySeconds(86400)
                    // 发放授权码code 注册的回调地址
                    .redirectUris("http://localhost:8031/client-web/login")
                    // 授权模式类型
                    .authorizedGrantTypes("refresh_token", "authorization_code")
                    // 针对 authorization_code 是否自动Approval 操作，（略过点击确认按钮）
                    // .autoApprove(true)
                    // 授权的令牌权限
                    .scopes("read", "write")
                    .and()
                .withClient("auth-client-b")
                    .secret(passwordEncoder.encode("123456"))
                    .resourceIds("resourceApp-b")
                    .accessTokenValiditySeconds(7200)
                    .refreshTokenValiditySeconds(86400)
                    // 发放授权码code 注册的回调地址
                    .redirectUris("http://localhost:8032/client-admin/login")
                    // 授权模式类型
                    .authorizedGrantTypes("refresh_token", "authorization_code")
                    // 针对 authorization_code 是否自动Approval 操作，（略过点击确认按钮）
                    // .autoApprove(true)
                    // 授权的令牌权限
                    .scopes("read", "write");
    }

    //    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//            .withClient("authClient1").secret(passwordEncoder.encode("123456"))
//
//                .scopes("read", "write")
//
//                .resourceIds("resourceApp1")
//                // 令牌注册的回调地址
//                .redirectUris("http://cn.bing.com")
//
//                .authorizedGrantTypes("authorization_code")
//                // 是否自动Approval 操作，（略过点击确认按钮）
//                .autoApprove(true)
//
//                .accessTokenValiditySeconds(3600)
//                // 令牌刷新的有效期
//                .refreshTokenValiditySeconds(7200)
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager)
//                .tokenStore(tokenStore())
//                .tokenEnhancer(jwtAccessTokenConverter());
//
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(endpoints.getTokenStore());
//        // 是否支持刷新Token
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setReuseRefreshToken(true);
//        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
//        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        //设置accessToken和refreshToken的默认超时时间(如果clientDetails的为null就取默认的，如果clientDetails的不为null取clientDetails中的)
//        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(2));
//        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
//    }
}
