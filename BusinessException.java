package com.project.hotel_api_p2.Infra.Security.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
