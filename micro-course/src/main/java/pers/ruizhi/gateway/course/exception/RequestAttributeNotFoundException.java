package pers.ruizhi.gateway.course.exception;

import lombok.Getter;

/**
 * @Description
 * @Author Chris
 * @Date 2024/8/4
 */
@Getter
public class RequestAttributeNotFoundException extends RuntimeException{

    private final String key;

    public RequestAttributeNotFoundException(String key) {
        super(String.format("Attribute %s not found", key));
        this.key = key;
    }
}
