package pers.ruizhi.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import static pers.ruizhi.auth.Constant.TOKEN_SECRET_KEY;

/**
 * @Description
 * @Author Ruizhi Li
 * @Date 2024/7/8
 */
@Configuration
public class TokenConfig {


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(tokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(TOKEN_SECRET_KEY);

        return converter;
    }
}
