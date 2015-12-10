package com.sgec.exception;

@SuppressWarnings("serial")
public class ConnectionNotFoundException extends Exception {

    public ConnectionNotFoundException() {
    }

    public ConnectionNotFoundException(String str) {
        super(str);
    }

    public ConnectionNotFoundException(Exception ex) {
        super("Cannot get connection: " + ex);
    }

}
