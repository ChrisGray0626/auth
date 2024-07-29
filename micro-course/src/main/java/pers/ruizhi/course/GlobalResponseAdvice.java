package pers.ruizhi.course;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pers.ruizhi.common.domain.Response;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Chris
 * @Date 2024/7/29
 */
@RestControllerAdvice(basePackages = "pers.ruizhi.course")
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * Open the function
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * Handle the response data
     */
    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // Handle the string data
        if (data instanceof String) {
            try {
                return objectMapper.writeValueAsString(Response.success(data));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else if (data instanceof byte[]) {
            return data;
        } else if (data instanceof Response) {
            return data;
        }
        return Response.success(data);
    }
}
