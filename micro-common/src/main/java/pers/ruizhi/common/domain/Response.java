package pers.ruizhi.common.domain;

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

    public Response(ResponseEnum responseEnum) {
        this(responseEnum, null);
    }

    public Response(ResponseEnum responseEnum, Object data) {
        this(responseEnum.code, responseEnum.msg, data);
    }

    public Response(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response success() {
        return success(null);
    }

    public static Response success(Object data) {
        return new Response(ResponseEnum.SUCCESS, data);
    }

    public static Response error() {
        return error(null);
    }

    public static Response error(Object data) {
        return new Response(ResponseEnum.ERROR, data);
    }
}