package com.usl.delivery_app.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
