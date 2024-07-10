package pers.ruizhi.auth.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pers.ruizhi.auth.domain.Response;
import pers.ruizhi.auth.domain.ResponseEnum;
import pers.ruizhi.auth.domain.UserDetail;
import pers.ruizhi.auth.domain.UserLoginDto;
import pers.ruizhi.auth.exception.AuthenticationFailException;
import pers.ruizhi.auth.util.JwtUtil;
import pers.ruizhi.auth.util.RedisCacheUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static pers.ruizhi.auth.Constant.DEFAULT_EXPIRE_TIME;
import static pers.ruizhi.auth.Constant.LOGIN_KEY_PREFIX;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Override
    public Response login(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
        // Check the authentication
        if (ObjectUtils.isEmpty(authentication)) {
            throw new AuthenticationFailException();
        }
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        // Save the user detail to the cache
        redisCacheUtil.set(LOGIN_KEY_PREFIX + userDetail.getUsername(), userDetail, DEFAULT_EXPIRE_TIME);
        // Create the token
        String token = JwtUtil.createToken(userDetail.getUsername());
        // TODO Add token to Header
        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        return new Response(ResponseEnum.LOGIN_SUCCESS, map);
    }

    @Override
    public Response logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        // Remove the user detail from the cache
        redisCacheUtil.remove(LOGIN_KEY_PREFIX + userDetail.getUsername());

        return new Response(ResponseEnum.LOGOUT_SUCCESS);
    }
}
