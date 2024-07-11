package pers.ruizhi.auth.service;

import pers.ruizhi.auth.domain.UserLoginDto;
import pers.ruizhi.common.domain.Response;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
public interface UserService {

    Response login(UserLoginDto userLoginDto);

    Response logout();
}
