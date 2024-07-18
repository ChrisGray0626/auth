package pers.ruizhi.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.ruizhi.Constant;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Chris
 * @Date 2024/7/18
 */
@Component
@Order(1)
@Slf4j
public class UserHandlerFilter implements GlobalFilter {

    @Resource
    private TokenStore tokenStore;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uri = exchange
                .getRequest()
                .getURI()
                .toString();
        // Ignore token request
        if (StringUtils.contains(uri, "/oauth/token")) {
            return chain.filter(exchange);
        }
        String token = extractToken(exchange);
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        // Save information to exchange
        oAuth2AccessToken
                .getAdditionalInformation()
                .forEach(
                        (k, v) ->
                                exchange
                                        .getAttributes()
                                        .put(k, v)
                );
        return chain.filter(exchange);
    }

    private String extractToken(ServerWebExchange exchange) {
        String token = exchange
                .getRequest()
                .getHeaders()
                .getFirst(Constant.HEADER_AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return token.replace(Constant.PREFIX_BEARER, "");
    }
}
