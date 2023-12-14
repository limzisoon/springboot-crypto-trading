package com.project.trading.exception;

public class CustomException extends Exception {

    static final long serialVersionUID = 0L;

    public CustomException(String debugMessage) {
        super(debugMessage);
    }

    public CustomException(String debugMessage, Throwable t) {
        super(debugMessage, t);
    }
}