package com.kitri.todo.config;

import com.kitri.todo.interceptor.CheckUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckUserInterceptor())
                .addPathPatterns("**/todos/**", "/todos/**");
    }
}
