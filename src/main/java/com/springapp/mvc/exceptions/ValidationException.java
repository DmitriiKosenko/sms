package com.springapp.mvc.exceptions;

/**
 * Created by dmitry on 04.10.15.
 */
public class ValidationException extends Exception {

    private final String message;

    public ValidationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
