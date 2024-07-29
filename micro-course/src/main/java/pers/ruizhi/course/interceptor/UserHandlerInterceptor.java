package pers.ruizhi.course.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import pers.ruizhi.course.dao.StudentRepo;
import pers.ruizhi.course.domain.Student;
import pers.ruizhi.course.exception.EntityNotFoundException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static pers.ruizhi.course.Constant.*;

/**
 * @Description
 * @Author Chris
 * @Date 2024/7/26
 */
@Component
public class UserHandlerInterceptor implements HandlerInterceptor {

    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private StudentRepo studentRepo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userJson = request.getHeader(HEADER_KEY_USER);
        // Check if user info exists
        if (ObjectUtils.isEmpty(userJson)) {
            return false;
        }
        // Convert user json to object
        Map<String, Object> user;
        try {
            user = objectMapper.readValue(userJson, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // Check if user exists
        if (!user.containsKey(USER_KEY_ROLE)) {
            return false;
        }
        String role = (String) user.get(USER_KEY_ROLE);
        if (!user.containsKey(USER_KEY_ID)) {
            return false;
        }
        Integer userId = (Integer) user.get(USER_KEY_ID);
        switch (role) {
            case ROLE_TYPE_STUDENT:
                Student student = studentRepo.findByUserId(userId);
                if (ObjectUtils.isEmpty(student)) {
                    throw new EntityNotFoundException(ENTITY_STUDENT, userId);
                }
                // Save to attribute
                request.setAttribute(ATTRIBUTE_KEY_STUDENT, student);
            default:
        }
        return true;
    }
}
