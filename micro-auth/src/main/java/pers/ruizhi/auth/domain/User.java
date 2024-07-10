package pers.ruizhi.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userId;
    private String username;
    private String password;
    private String email;
}
