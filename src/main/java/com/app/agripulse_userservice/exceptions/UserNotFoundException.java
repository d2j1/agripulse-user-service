package com.app.agripulse_userservice.exceptions;

public class UserNotFoundException extends RuntimeException {

    private String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
