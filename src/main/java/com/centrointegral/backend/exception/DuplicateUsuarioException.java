package com.centrointegral.backend.exception;

public class DuplicateUsuarioException extends RuntimeException {
    public DuplicateUsuarioException(String message) {
        super(message);
    }
}
