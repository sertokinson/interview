package ru.interview.platform.domain.exception;

public class ServerException extends RuntimeException {
    public ServerException(String message, Object... args) {
        super(String.format(message, args));
    }
}
