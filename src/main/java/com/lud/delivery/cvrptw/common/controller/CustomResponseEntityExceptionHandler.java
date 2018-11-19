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
import com.lud.delivery.cvrptw.common.exception.resolver.MessageResolver;

/**
 * Handles all REST api's response when exceptions are caught 
 *
 * @author Ludmar Ribeiro
 *
 */
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    /**
     * Exception message resolver
     */
    @Autowired
    private MessageResolver messageResolver;

    /**
     * Handles {@link NotFoundException}
     *
     * @param ex
     * @param request
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest request) {

        ErrorRequestBody body = createBody(ex, request, HttpStatus.NOT_FOUND);

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Handles {@link ObjectExistsForIdException}
     *
     * @param ex
     * @param request
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(ObjectExistsForIdException.class)
    protected ResponseEntity<Object> handleAlreadyExists(ObjectExistsForIdException ex, WebRequest request) {

        ErrorRequestBody body = createBody(ex, request, HttpStatus.CONFLICT);

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     * Handles {@link MethodArgumentTypeMismatchException}
     *
     * @param ex
     * @param request
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleRequestArgumentParseProblem(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ErrorRequestBody body = createBody(ex, request, HttpStatus.BAD_REQUEST);

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    /**
     * Handles {@link MethodArgumentNotValidException}
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return {@link ResponseEntity}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorRequestBody body = createBody(ex, request, HttpStatus.BAD_REQUEST);

        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handles {@link HttpMessageNotReadableException}
     *
     * Redirects to handleNotFound(ex, request) when a {@link NotFoundException} is
     * identified as the cause 
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return {@link ResponseEntity}
     */
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

    /**
     * Creates the response entity's body
     *
     * @param ex
     * @param request
     * @param status
     * @return {@link ErrorRequestBody}
     */
    private ErrorRequestBody createBody(Exception ex, WebRequest request, HttpStatus status) {
        String message = messageResolver.resolveMessage(ex);

        List<String> messages = resolveFieldErrors(ex);

        return createBody(request, status, message, messages);
    }

    /**
     * Creates the response entity's body
     *
     * @param request
     * @param status
     * @param message
     * @param messages
     * @return {@link ErrorRequestBody}
     */
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

    /**
     * Handles {@link FieldError} messages when the exception contains it
     *
     * @param ex
     * @return {@link List} of {@link String}
     */
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

    /**
     * Resolve the message of a {@link FieldError}
     *
     * @param fieldError
     * @return {@link String}
     */
    private String resolveMessage(FieldError fieldError) {
        
        String field = fieldError.getField();
        String message = fieldError.getDefaultMessage();
        Object value = fieldError.getRejectedValue();
        
        return MessageFormat.format(message, field, value);
    }
 }
