package pers.ruizhi.config;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.ruizhi.common.domain.Response;
import pers.ruizhi.common.domain.ResponseEnum;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/12
 */
@Component
@Slf4j
public class AccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException e) {
        return Mono
                .defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> {
                    response
                            .getHeaders()
                            .setContentType(MediaType.APPLICATION_JSON);
                    Response res;
                    res = new Response(ResponseEnum.ACCESS_DENIED_ERROR);
                    return response.writeWith(Mono.fromSupplier(() -> {
                        DataBufferFactory bufferFactory = response.bufferFactory();
                        try {
                            return bufferFactory.wrap(new ObjectMapper().writeValueAsBytes(res));
                        } catch (IOException ex) {
                            log.error("Error writing response", ex);
                            return bufferFactory.wrap(new byte[0]);
                        }
                    }));
                });
    }
}
