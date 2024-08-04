package pers.ruizhi.gateway.course;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.ruizhi.gateway.common.domain.Response;
import pers.ruizhi.gateway.common.domain.ResponseEnum;
import pers.ruizhi.gateway.course.exception.RequestAttributeNotFoundException;
import pers.ruizhi.gateway.course.exception.EntityNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public Response handle(EntityNotFoundException e) {
        Map<String, Object> data = new HashMap<>();
        data.put("entity", e.getEntity());
        data.put("id", e.getId());
        return new Response(ResponseEnum.ENTITY_NOT_FOUND_ERROR, data);
    }

    @ExceptionHandler(RequestAttributeNotFoundException.class)
    public Response handle(RequestAttributeNotFoundException e) {
        Map<String, Object> data = new HashMap<>();
        data.put("key", e.getKey());
        return new Response(ResponseEnum.REQUEST_ATTRIBUTE_NOT_FOUND_ERROR, data);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public Response handle(RuntimeException e) {
//        String message = e.getMessage();
//        log.error(message);
//        return Response.error(message);
//    }
}
