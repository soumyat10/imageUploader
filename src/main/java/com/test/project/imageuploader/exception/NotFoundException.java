package com.test.project.imageuploader.exception;

public class NotFoundException extends RuntimeException {

    String errorMessage;

    public NotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;

    }
}
