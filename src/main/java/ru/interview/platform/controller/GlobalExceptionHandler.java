package ru.interview.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.interview.platform.domain.dto.Error;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleIllegalArgumentExceptions(Exception exception, WebRequest webRequest) {
        HttpStatus errorCode = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("trace = {}, error = {}", exception.getStackTrace(), exception.getMessage());
        return this.handleExceptionInternal(exception, new Error(exception.getMessage()), new HttpHeaders(), errorCode, webRequest);
    }
}