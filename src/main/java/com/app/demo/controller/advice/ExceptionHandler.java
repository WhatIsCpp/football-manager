package com.app.demo.controller.advice;

import com.app.demo.exceptions.EntityNotFoundException;
import com.app.demo.exceptions.FootballPlayerNotLegalException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {
        String message = e.getMessage();
        String causeMessage = "";

        if (e.getCause() != null) {
            causeMessage = e.getCause().getMessage();
        }
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiError apiError = new ApiError(status.toString(), message, e.getClass().getSimpleName(), causeMessage);

        return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler({EntityNotFoundException.class, FootballPlayerNotLegalException.class})
    protected ResponseEntity<Object> handleDataTypeException(RuntimeException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return handleExceptionInternal(e, new ApiError(status.toString(), e.getMessage(), e.getClass().getSimpleName(), ""), new HttpHeaders(), status, request);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ApiError {
        String status;
        String message;
        String exception;
        String detail;
    }
}
