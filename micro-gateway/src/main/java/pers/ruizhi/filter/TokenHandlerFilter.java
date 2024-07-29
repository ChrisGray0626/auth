package pers.ruizhi.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.ruizhi.Constant;
import reactor.core.publisher.Mono;

/**
 * @Description
 * @Author Chris
 * @Date 2024/7/26
 */
@Component
@Order()
public class TokenHandlerFilter implements GlobalFilter {
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
        // Remove token
        exchange
                .getRequest()
                .mutate()
                .headers(
                        httpHeaders -> httpHeaders.remove(Constant.HEADER_KEY_AUTHORIZATION)
                );
        return chain.filter(exchange);
    }
}
