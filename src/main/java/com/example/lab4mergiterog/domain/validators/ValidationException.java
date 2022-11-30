package com.example.lab4mergiterog.domain.validators;

public class ValidationException extends RuntimeException {
    /**
     * Default constructor
     */
    public ValidationException() {
    }

    /**
     * Throws an error message
     * @param message string - error message
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Throws error message and cause
     * @param message string - error message
     * @param cause Throwable - error cause
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Throws error cause
     * @param cause Throwable - error cause
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause, boolean enableSupression, boolean writeableStackTrace) {
        super(message,cause,enableSupression,writeableStackTrace);
    }
}
