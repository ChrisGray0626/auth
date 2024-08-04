package pers.ruizhi.gateway.config;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @Description Authorization Manager
 * @Author RuiZhi Li
 * @Date 2024/7/12
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
        // TODO Config authorization
        return authentication
                .map(auth -> new AuthorizationDecision(true))
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
