package ru.interview.platform.domain.exception;

public class ValidateException extends RuntimeException {
    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Object... args) {
        super(String.format(message, args));
    }
}
