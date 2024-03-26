package com.amdrejr.springsecurityjwt.exceptions.customExceptions;

public class UsernameAlreadyExistsExceprion extends RuntimeException {
    public UsernameAlreadyExistsExceprion(String msg) {
        super(msg);
    }
}
