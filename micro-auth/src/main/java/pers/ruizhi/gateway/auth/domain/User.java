package pers.ruizhi.gateway.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pers.ruizhi.gateway.common.domain.BaseEntity;

import javax.persistence.Entity;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    private String username;
    private String password;
    private String role;

}
