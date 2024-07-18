package pers.ruizhi.auth.filter;


import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pers.ruizhi.auth.Constant;
import pers.ruizhi.auth.domain.UserDetail;
import pers.ruizhi.auth.exception.AuthenticationFailException;
import pers.ruizhi.auth.service.UserDetailServiceImpl;
import pers.ruizhi.auth.util.JwtUtil;
import pers.ruizhi.auth.util.RedisCacheUtil;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
// @Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Resource
    private UserDetailServiceImpl userDetailsService;
    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(Constant.HEADER_AUTHORIZATION);
        // Check the existent of the token
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);

            return;
        }
        String username = JwtUtil.extractSubject(token);
        // Get the user detail from the cache
        UserDetail userDetail = redisCacheUtil.get(Constant.LOGIN_KEY_PREFIX + username);
        // Check the existent of the user detail
        if (ObjectUtils.isEmpty(userDetail)) {
            throw new AuthenticationFailException();
        }
        // Set the authentication of the user detail
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
