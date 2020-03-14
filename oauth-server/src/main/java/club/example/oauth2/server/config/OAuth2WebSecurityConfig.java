package club.example.oauth2.server.config;

import club.example.oauth2.server.config.properties.OAuth2SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Order(1)
@Configuration
@EnableWebSecurity
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final OAuth2SecurityProperties securityProperties;

    @Autowired
    public OAuth2WebSecurityConfig(@Qualifier("authorizationUserDetailService") UserDetailsService userDetailsService,
                                   PasswordEncoder passwordEncoder, OAuth2SecurityProperties securityProperties) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.securityProperties = securityProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String loginPage = securityProperties.getBrowser().getLoginPage();
        http.formLogin()
            .loginPage(loginPage)
            .loginProcessingUrl("/sign-in")
        .and()
            .authorizeRequests()
            .antMatchers(
                    "/style/**",
                    "/scripts/***",
                    loginPage
            ).permitAll()
            .anyRequest()
            .authenticated()
        .and()
            .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
