package com.fatlab.service.excetions;

public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthorizationException(String msg,Throwable cause) {
        super(msg,cause);
    }

    public AuthorizationException(String msg) {
        super(msg);
    }

}