package pers.ruizhi.auth.domain;

import lombok.Data;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */
@Data
public class Response {
    private final String code;
    private final String msg;
    private final Object data;

    public Response(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response(ResponseEnum responseEnum, Object data) {
        this(responseEnum.code, responseEnum.msg, data);
    }

    public Response(ResponseEnum responseEnum) {
        this(responseEnum, null);
    }

    public static Response success() {
        return new Response(ResponseEnum.SUCCESS, null);
    }

    public static Response success(ResponseEnum responseEnum, Object data) {
        return new Response(responseEnum, data);
    }

    public static Response error() {
        return new Response(ResponseEnum.ERROR, null);
    }
}