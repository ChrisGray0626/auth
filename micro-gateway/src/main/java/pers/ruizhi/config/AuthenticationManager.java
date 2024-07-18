package pers.ruizhi.config;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/11
 */
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Resource
    private TokenStore tokenStore;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono
                .justOrEmpty(authentication)
                .filter(auth -> auth instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap(
                        (token) -> {
                            OAuth2AccessToken oAuth2AccessToken;
                            try {
                                oAuth2AccessToken = tokenStore.readAccessToken(token);
                            } catch (InvalidTokenException e) {
                                return Mono.error(new InvalidBearerTokenException(token));
                            }
                            if (oAuth2AccessToken.isExpired()) {
                                return Mono.error(new AccountExpiredException(token));
                            }
                            OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(token);
                            if (ObjectUtils.isEmpty(oAuth2Authentication)) {
                                return Mono.error(new InvalidBearerTokenException(token));
                            }
                            return Mono.just(oAuth2Authentication);
                        }
                )
                .cast(Authentication.class);
    }
}
