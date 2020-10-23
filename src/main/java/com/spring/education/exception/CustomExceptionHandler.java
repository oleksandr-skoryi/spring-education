package com.spring.education.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handler(CustomException exception) {
        log.error("ERROR OCCURRED", exception);
        ExceptionResponse error = new ExceptionResponse(400, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
