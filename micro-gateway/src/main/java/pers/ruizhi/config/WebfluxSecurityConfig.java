package pers.ruizhi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.annotation.Resource;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/11
 */
@Configuration
@EnableWebFluxSecurity
public class WebfluxSecurityConfig {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .oauth2ResourceServer()
                .jwt()
                .authenticationManager(authenticationManager)
                .and()
                .authenticationEntryPoint(authenticationEntryPoint);
        http
                .authorizeExchange()
                .pathMatchers("/oauth/token")
                .permitAll()
//                .anyExchange()
                // TODO Authorization
//                .access(authorizationManager)
//                .and()
//                .exceptionHandling()
                .and()
                .csrf()
                .disable();

        return http.build();
    }

}
