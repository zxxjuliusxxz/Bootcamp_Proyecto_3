package com.webflux.jfgb.webflux.Application.Models.Exception;
public class CustomBadRequestException extends RuntimeException {
    public CustomBadRequestException(String object) {
        super("BAD REQUEST EXCEPTION: " + object);
    }
}