package ru.interview.platform.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.interview.platform.domain.dto.Error;
import ru.interview.platform.domain.exception.ValidateException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest webRequest) {
        return exceptionHandler(HttpStatus.INTERNAL_SERVER_ERROR, exception, webRequest);
    }

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<Object> handleIllegalArgumentExceptions(ValidateException exception, WebRequest webRequest) {
        return exceptionHandler(HttpStatus.BAD_REQUEST, exception, webRequest);
    }

    private ResponseEntity<Object> exceptionHandler(HttpStatus errorCode, Exception exception, WebRequest webRequest) {
        log.error("trace = {}, error = {}", exception.getStackTrace(), exception.getMessage());
        return this.handleExceptionInternal(exception, new Error(exception.getMessage()), new HttpHeaders(), errorCode, webRequest);
    }
}