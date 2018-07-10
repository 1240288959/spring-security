package com.example.security_demo.exception;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeFailureException extends AuthenticationException {
    public ValidateCodeFailureException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeFailureException(String msg) {
        super(msg);
    }
}
