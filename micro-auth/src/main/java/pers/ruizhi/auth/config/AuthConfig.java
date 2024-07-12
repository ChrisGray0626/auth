package pers.ruizhi.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import pers.ruizhi.auth.service.UserDetailServiceImpl;

import javax.annotation.Resource;
import java.util.Arrays;

import static pers.ruizhi.auth.Constant.*;

/**
 * @Description
 * @Author Ruizhi Li
 * @Date 2024/7/8
 */
@Configuration
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailServiceImpl userDetailService;
    @Resource
    private ClientDetailsService clientDetailsService;
    @Resource
    private TokenStore tokenStore;
    @Resource
    private JwtTokenEnhancer jwtTokenEnhancer;
    @Resource
    private JwtAccessTokenConverter tokenConverter;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                // /oauth/token_key API
                .tokenKeyAccess("permitAll()")
                // /oauth/check_token API
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(AUTH_CLIENT)
                .secret(passwordEncoder.encode(AUTH_SECRET))
                .resourceIds(AUTH_RESOURCE_ID)
                .scopes(AUTH_SCOPE)
                .authorizedGrantTypes(AUTH_GRANT_PASSWORD, AUTH_GRANT_REFRESH_TOKEN)
                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(new InMemoryAuthorizationCodeServices())
                .tokenServices(tokenServices())
                .userDetailsService(userDetailService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
        tokenServices.setRefreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer, tokenConverter));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);

        return tokenServices;
    }
}
