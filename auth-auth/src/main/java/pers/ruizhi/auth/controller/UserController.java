package pers.ruizhi.auth.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.ruizhi.auth.domain.Response;
import pers.ruizhi.auth.domain.UserLoginDto;
import pers.ruizhi.auth.service.UserService;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/user/login")
    public Response login(@RequestBody UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }

    @GetMapping("/user/logout")
    public Response logout() {
        return userService.logout();
    }
}
