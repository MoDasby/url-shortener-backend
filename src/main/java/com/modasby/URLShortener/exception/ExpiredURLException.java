package com.modasby.URLShortener.exception;

public class ExpiredURLException extends RuntimeException {
    public ExpiredURLException(String message) {
        super(message);
    }

    public ExpiredURLException(String message, Throwable cause) {
        super(message, cause);
    }
}
