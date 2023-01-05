package com.test.project.imageuploader.intercepter;

import com.test.project.imageuploader.entity.UserEntity;
import com.test.project.imageuploader.exception.AuthenticationException;
import com.test.project.imageuploader.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String handlerMethodName = handlerMethod.getMethod().getName();
        if (skipAuthentication(handlerMethodName)) {
            log.info("skipping authorisation for request with method: {} and uri: {}",
                    request.getMethod(), request.getRequestURI());
            return true;
        }
        log.info("performing authorisation for request with method: {} and uri: {}",
                request.getMethod(), request.getRequestURI());
        if (request.getHeader("authorization") == null
                || request.getHeader("authorization").split(" ") == null) {
            throw new AuthenticationException("Authentication token not present");
        }

        String token = request.getHeader("authorization");
        String pair = new String(Base64.decodeBase64(token.substring(6)));
        String userName = pair.split(":")[0];
        String password = pair.split(":")[1];

        Optional<UserEntity> optionalUserEntity = userRepository.findByUsernameAndEnabled(userName, true);
        if (optionalUserEntity.isEmpty() || !optionalUserEntity.get().getPassword().equals(password)) {
            throw new AuthenticationException("User not authenticated");
        }
        return true;

    }

    private boolean skipAuthentication(String handlerMethodName) {
        return getMethodsToSkipAuth().contains(handlerMethodName);
    }

    private List<String> getMethodsToSkipAuth() {
        return Arrays.asList("registerUser");
    }

}