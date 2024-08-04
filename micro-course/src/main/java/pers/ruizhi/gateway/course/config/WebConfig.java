package pers.ruizhi.gateway.course.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.ruizhi.gateway.course.interceptor.UserHandlerInterceptor;

import javax.annotation.Resource;

/**
 * @Description
 * @Author RuiZhi Li
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
