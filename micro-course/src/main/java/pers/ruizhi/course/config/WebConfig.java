package pers.ruizhi.course.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.ruizhi.course.interceptor.UserHandlerInterceptor;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Chris
 * @Date 2024/7/26
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private UserHandlerInterceptor userHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(userHandlerInterceptor)
                .addPathPatterns("/**");
    }
}
