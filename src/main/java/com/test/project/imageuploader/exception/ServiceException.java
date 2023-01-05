package com.test.project.imageuploader.exception;

public class ServiceException extends RuntimeException {

    String errorMessage;

    public ServiceException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;

    }
}
