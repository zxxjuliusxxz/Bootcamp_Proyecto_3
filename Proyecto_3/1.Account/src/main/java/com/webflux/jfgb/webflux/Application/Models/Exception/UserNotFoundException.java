package com.webflux.jfgb.webflux.Application.Models.Exception;
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String object) { super(object); }
}