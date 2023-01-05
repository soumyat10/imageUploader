package com.test.project.imageuploader.exception;

import com.test.project.imageuploader.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiResponse> notFoundException(NotFoundException ex, WebRequest request) {
        ApiResponse apiResponse = ApiResponse.withNoData();
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setCode(0);
        apiResponse.setStatus("FAIL");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ApiResponse> authentication(AuthenticationException ex, WebRequest request) {
        ApiResponse apiResponse = ApiResponse.withNoData();
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setCode(0);
        apiResponse.setStatus("FAIL");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }

}

