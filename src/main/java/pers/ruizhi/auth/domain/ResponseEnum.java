package pers.ruizhi.auth.domain;

import lombok.AllArgsConstructor;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS("20000", "success"),
    LOGIN_SUCCESS("20001", "Login successfully."),
    LOGOUT_SUCCESS("20002", "Logout successfully."),
    ERROR("50000", "fail");

    public final String code;
    public final String msg;

}
