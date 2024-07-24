package com.app.agripulse_userservice.exceptions;


public class IncorrectPassswordException extends RuntimeException {

    private String message;

    public IncorrectPassswordException(String message) {
        super(message);
        this.message = message;
    }

}
