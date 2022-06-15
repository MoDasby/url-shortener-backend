package com.modasby.URLShortener.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class URLShortenerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidURLException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidURLException(InvalidURLException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ExpiredURLException.class)
    public ResponseEntity<ExceptionResponse> handleExpiredURLException(ExpiredURLException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
