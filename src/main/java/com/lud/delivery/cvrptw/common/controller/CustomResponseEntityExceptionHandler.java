package com.lud.delivery.cvrptw.common.controller;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lud.delivery.cvrptw.common.domain.ErrorRequestBody;
import com.lud.delivery.cvrptw.common.domain.ErrorRequestBody.ErrorRequestBodyBuilder;
import com.lud.delivery.cvrptw.common.exception.NotFoundException;
import com.lud.delivery.cvrptw.common.exception.ObjectExistsForIdException;
import com.lud.delivery.cvrptw.common.exception.resolver.MessageResolverRouter;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @Autowired
    private MessageResolverRouter messageResolver;

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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleRequestArgumentParseProblem(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ErrorRequestBody body = createBody(ex, request, HttpStatus.BAD_REQUEST);

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
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

        ErrorRequestBody body = createBody(ex, request, HttpStatus.BAD_REQUEST);

        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
    }

    private ErrorRequestBody createBody(Exception ex, WebRequest request, HttpStatus status) {
        String message = messageResolver.resolveMessage(ex);

        List<String> messages = resolveFieldErrors(ex);

        return createBody(request, status, message, messages);
    }

    private ErrorRequestBody createBody(WebRequest request, HttpStatus status, String message, List<String> messages) {
        String uri = ((ServletWebRequest) request)
                .getRequest()
                .getRequestURI();

        return new ErrorRequestBodyBuilder()
                .setStatus(status)
                .setMessage(message)
                .setErrors(messages)
                .setPath(uri)
                .setTimestamp(LocalDateTime.now())
                .build();
    }

    private List<String> resolveFieldErrors(Exception ex) {

        if(ex instanceof MethodArgumentNotValidException)
            return ((MethodArgumentNotValidException) ex)
                .getBindingResult()
                    .getFieldErrors()
                        .stream()
                            .map(e -> resolveMessage(e))
                    .collect(Collectors.toList());

        return null;
    }

    private String resolveMessage(FieldError fieldError) {
        
        String field = fieldError.getField();
        String message = fieldError.getDefaultMessage();
        Object value = fieldError.getRejectedValue();
        
        return MessageFormat.format(message, field, value);
    }
 }
