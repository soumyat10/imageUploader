package com.test.project.imageuploader.exception;
public class AuthenticationException extends RuntimeException {

    String errorMessage;

    public AuthenticationException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;

    }
}
