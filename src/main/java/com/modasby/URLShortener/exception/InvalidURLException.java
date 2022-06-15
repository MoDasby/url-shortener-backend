package com.modasby.URLShortener.exception;

public class InvalidURLException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidURLException(String message) {
        super(message);
    }

    public InvalidURLException(String message, Throwable cause) {
        super(message, cause);
    }
}
