package com.test.project.imageuploader.config;

import com.test.project.imageuploader.intercepter.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).excludePathPatterns(excludePathPattern()).addPathPatterns("/**");
    }

    private List<String> excludePathPattern() {

        List<String> pathArray = Arrays.asList(
                "/h2-console/**");
        return pathArray;
    }
}
