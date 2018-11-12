package com.lud.delivery.cvrptw.common.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lud.delivery.cvrptw.common.domain.ErrorRequestBody;
import com.lud.delivery.cvrptw.common.domain.ErrorRequestBody.ErrorRequestBodyBuilder;
import com.lud.delivery.cvrptw.common.exception.ArgumentedException;
import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.common.exception.ObjectExistsForIdException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest request) {

        ErrorRequestBody body = createBody(ex, request, HttpStatus.NOT_FOUND);

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ObjectExistsForIdException.class)
    protected ResponseEntity<Object> handleAlreadyExists(ObjectExistsForIdException ex, WebRequest request) {

        ErrorRequestBody body = createBody(ex, request, HttpStatus.CONFLICT);

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorRequestBody body = createBody(ex, request, HttpStatus.BAD_REQUEST);

        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable exception = ex;

        while(exception.getCause() != null) {
            exception = exception.getCause();

            if(exception instanceof NotFoundException)
                return handleNotFound((NotFoundException) exception, request); 
        }

        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }
    
    private ErrorRequestBody createBody(ArgumentedException ex, WebRequest request, HttpStatus status) {
        String message = messageSource
                .getMessage(ex.getClass().getName(), 
                        ex.getArgs(), 
                        Locale.getDefault());

        return new ErrorRequestBodyBuilder()
                .setStatus(status)
                .setMessage(message)
                .setPath(request.getContextPath())
                .setTimestamp(new Date())
                .build();
    }

    private ErrorRequestBody createBody(MethodArgumentNotValidException ex, WebRequest request, HttpStatus status) {

        String message = messageSource
                .getMessage(ex.getClass().getName(),
                        new Object[] {ex.getBindingResult().getObjectName().toString()}, 
                        Locale.getDefault());

        List<String> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> resolveMessage(e))
                .collect(Collectors.toList());

        return new ErrorRequestBodyBuilder()
                .setStatus(status)
                .setMessage(message)
                .setErrors(messages)
                .setPath(request.getContextPath())
                .setTimestamp(new Date())
                .build();
    }

    private String resolveMessage(FieldError fieldError) {
        
        String field = fieldError.getField();
        String message = fieldError.getDefaultMessage();
        Object value = fieldError.getRejectedValue();
        
        return MessageFormat.format(message, field, value);
    }
 }
