package pers.ruizhi.auth.service;

import pers.ruizhi.auth.domain.Response;
import pers.ruizhi.auth.domain.UserLoginDto;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
public interface UserService {

    Response login(UserLoginDto userLoginDto);

    Response logout();
}
