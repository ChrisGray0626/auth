package pers.ruizhi.course.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/30
 */
public class RequestUtil {

    public static void setAttribute(String key, Object value) {
        RequestContextHolder
                .currentRequestAttributes()
                .setAttribute(key, value, RequestAttributes.SCOPE_REQUEST);
    }

    public static Object getAttribute(String key) {
        return RequestContextHolder
                .currentRequestAttributes()
                .getAttribute(key, RequestAttributes.SCOPE_REQUEST);
    }
}
