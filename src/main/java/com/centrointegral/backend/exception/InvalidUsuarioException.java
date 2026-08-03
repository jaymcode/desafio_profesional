package com.centrointegral.backend.exception;

public class InvalidUsuarioException extends RuntimeException {
    public InvalidUsuarioException(String message) {
        super(message);
    }
}
