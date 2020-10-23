package com.spring.education.exception;

public class CustomException extends RuntimeException {

    public CustomException(final String message) {
        super(message);
    }
}
