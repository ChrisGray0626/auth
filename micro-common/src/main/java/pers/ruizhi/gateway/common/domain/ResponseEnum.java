package pers.ruizhi.gateway.common.domain;

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
    ERROR("50000", "fail"),
    INVALID_TOKEN_ERROR("50001", "Token is invalid."),
    EXPIRED_TOKEN_ERROR("50002", "Token is expired."),
    DENIED_ACCESS_ERROR("50003", "Access is Denied."),
    AUTHENTICATION_NOT_FOUND_ERROR("50004", "Authentication not found."),
    ENTITY_NOT_FOUND_ERROR("50005", "Entity not found."),
    REQUEST_ATTRIBUTE_NOT_FOUND_ERROR("50006", "Request attribute not found."),
    ;

    public final String code;
    public final String msg;

}
