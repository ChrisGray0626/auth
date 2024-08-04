package pers.ruizhi.gateway.auth.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pers.ruizhi.gateway.auth.dao.UserRepo;
import pers.ruizhi.gateway.auth.domain.User;
import pers.ruizhi.gateway.auth.domain.UserDetail;
import pers.ruizhi.gateway.auth.exception.UserNotFoundException;

import javax.annotation.Resource;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserRepo userRepo;

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException();
        }
        return new UserDetail(user);
    }
}
