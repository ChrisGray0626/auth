package pers.ruizhi.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
@Data
@AllArgsConstructor
public class UserLoginDto {

    private String username;
    private String password;
}
