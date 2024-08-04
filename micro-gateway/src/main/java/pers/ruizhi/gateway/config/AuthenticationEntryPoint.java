package pers.ruizhi.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.ruizhi.gateway.common.domain.Response;
import pers.ruizhi.gateway.common.domain.ResponseEnum;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @Description Authentication Exception Handler
 * @Author RuiZhi Li
 * @Date 2024/7/11
 */
@Component
@Slf4j
public class AuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        return Mono
                .defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> {
                    response
                            .getHeaders()
                            .setContentType(MediaType.APPLICATION_JSON);

                    Response res;
                    if (e instanceof InvalidBearerTokenException) {
                        res = new Response(ResponseEnum.INVALID_TOKEN_ERROR);
                    } else if (e instanceof AccountExpiredException) {
                        res = new Response(ResponseEnum.EXPIRED_TOKEN_ERROR);
                    } else if (e instanceof AuthenticationCredentialsNotFoundException) {
                        res = new Response(ResponseEnum.AUTHENTICATION_NOT_FOUND_ERROR);
                    } else {
                        log.error(e.getMessage());
                        res = new Response(ResponseEnum.ERROR);
                    }
                    return response.writeWith(Mono.fromSupplier(() -> {
                        DataBufferFactory bufferFactory = response.bufferFactory();
                        try {
                            //返回响应结果
                            return bufferFactory.wrap(new ObjectMapper().writeValueAsBytes(res));
                        } catch (IOException ex) {
                            log.error("Error writing response", ex);
                            return bufferFactory.wrap(new byte[0]);
                        }
                    }));
                });
    }
}
