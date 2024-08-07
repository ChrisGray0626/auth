package pers.ruizhi.course.util;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import pers.ruizhi.course.exception.RequestAttributeNotFoundException;

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
        Object value = RequestContextHolder
                .currentRequestAttributes()
                .getAttribute(key, RequestAttributes.SCOPE_REQUEST);
        if (ObjectUtils.isEmpty(value)) {
            throw new RequestAttributeNotFoundException(key);
        }
        return value;
    }
}
