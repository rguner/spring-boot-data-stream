package com.guner.exception;

public class CreateCheckedException extends Exception {

    private static final long serialVersionUID = -3194364350288391132L;

    public CreateCheckedException(String message) {
        super(message);
    }

    public CreateCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

}
