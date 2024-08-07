package pers.ruizhi.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.ruizhi.common.Response;
import pers.ruizhi.common.ResponseEnum;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @Description Denied Access Handler
 * @Author RuiZhi Li
 * @Date 2024/7/12
 */
@Component
@Slf4j
public class DeniedAccessHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException e) {
        return Mono
                .defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> {
                    response
                            .getHeaders()
                            .setContentType(MediaType.APPLICATION_JSON);
                    Response res;
                    res = new Response(ResponseEnum.DENIED_ACCESS_ERROR);
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
