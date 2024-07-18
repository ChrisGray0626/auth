package pers.ruizhi.filter;

import lombok.extern.slf4j.Slf4j;
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
 * @Date 2024/7/18
 */
@Component
@Order(2)
@Slf4j
public class ActionHistoryHandlerFilter implements GlobalFilter {

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
        // TODO Save action history
        Object user = exchange.getAttribute(Constant.ADDITIONAL_INFO_KEY_USER);
        log.info("User: {}, Action: {}", user, uri);
        // TODO Get response body
        return chain.filter(exchange);
    }
}
