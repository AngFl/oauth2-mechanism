package club.example.oauth2.server.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Order(3)
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter implements ApplicationContextAware {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final AuthorizationCodeServices authorizationCodeService;

    private ApplicationContext applicationContext;

    @Autowired
    public OAuth2AuthorizationServerConfig(AuthenticationManager authenticationManager,
                                           AuthorizationCodeServices authorizationCodeService,
                                           @Qualifier("authorizationUserDetailService") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.authorizationCodeService = authorizationCodeService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()")
                //  接受token的使用方 需要用 key 来验证签名，需要对外暴露服务来获取签名
                .tokenKeyAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenStore jwtTokenStore = applicationContext.getBean("jwtTokenStore", TokenStore.class);
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .authorizationCodeServices(authorizationCodeService)
                .tokenStore(jwtTokenStore);
        JwtAccessTokenConverter tokenConverter = applicationContext.getBean("jwtAccessTokenConverter",
                JwtAccessTokenConverter.class);
        TokenEnhancer tokenEnhancer = applicationContext.getBean("jwtTokenEnhancer",
                TokenEnhancer.class);
        // Token 增强配置
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(tokenConverter, tokenEnhancer));
        endpoints
                .accessTokenConverter(tokenConverter)
                .tokenEnhancer(enhancerChain);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(
                applicationContext.getBean("mybatisOAuthClientsDetailService", ClientDetailsService.class)
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /*
     * // 内存模式下的 客户端信息
    // @Override
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
                .autoApprove(true)
                // 授权的令牌权限
                .scopes("read", "write");
    }**/
}
