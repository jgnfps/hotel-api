package com.project.hotel_api_p2.Infra.Security.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
