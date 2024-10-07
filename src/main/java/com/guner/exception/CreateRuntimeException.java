package com.guner.exception;

public class CreateRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -3194364350288391132L;

    public CreateRuntimeException(String message) {
        super(message);
    }

    public CreateRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
