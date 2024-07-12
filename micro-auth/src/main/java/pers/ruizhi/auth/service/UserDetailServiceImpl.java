package pers.ruizhi.auth.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pers.ruizhi.auth.domain.UserDetail;
import pers.ruizhi.auth.exception.UserNotFoundException;

import static pers.ruizhi.auth.Constant.USER;
import static pers.ruizhi.auth.Constant.USERNAME;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    // TODO UserDetailServiceImpl
    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {

//         TODO Get user details
        if (StringUtils.equals(username, USERNAME)) {
            return new UserDetail(USER, "admin");
        } else {
            throw new UserNotFoundException();
        }
    }
}
