package com.amdrejr.springsecurityjwt.exceptions.customExceptions;

public class ExpiredJwtException extends RuntimeException {
    public ExpiredJwtException(String message) {
        super(message);
    }
}
