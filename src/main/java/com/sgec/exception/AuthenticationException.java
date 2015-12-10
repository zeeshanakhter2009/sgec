package com.sgec.exception;

/**
 *
 * @author zeeshan
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
    }

    public AuthenticationException(String str) {
        super(str);
    }

    public AuthenticationException(Exception ex) {
        super("Failed to Authorize user : " + ex);
    }

}
