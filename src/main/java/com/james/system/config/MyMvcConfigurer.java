package com.james.system.config;

import com.james.system.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ACT
 */
@Configuration
public class MyMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/" ,"/admin/login", "/mobile/**"
                        , "/login.html", "/css/**", "/img/**", "/js/**", "/plugins/**", "/template/**");
    }

}
