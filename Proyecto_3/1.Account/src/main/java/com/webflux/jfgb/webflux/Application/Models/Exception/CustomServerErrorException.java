package com.webflux.jfgb.webflux.Application.Models.Exception;
public class CustomServerErrorException extends RuntimeException {
    public CustomServerErrorException(String object) {
        super("INTERNAL SERVER ERROR: " + object);
    }
}