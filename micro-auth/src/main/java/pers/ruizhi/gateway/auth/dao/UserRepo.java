package pers.ruizhi.gateway.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.ruizhi.gateway.auth.domain.User;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/25
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
