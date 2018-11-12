package com.lud.delivery.cvrptw.common.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ErrorRequestBody {

    private Date timestamp;
    private int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private String error;
    private String exception;
    private String message;
    private List<String> errors;
    private String trace;
    private String path;

    private ErrorRequestBody(
                Date timestamp,
                int status,
                String error,
                String exception,
                String message,
                List<String> errors,
                String trace,
                String path
            ) 
    {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.exception = exception;
        this.message = message;
        this.errors = errors;
        this.trace = trace;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getTrace() {
        return trace;
    }

    public String getPath() {
        return path;
    }

    public static class ErrorRequestBodyBuilder {
        private Date timestamp;
        private int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        private String error;
        private String exception;
        private String message;
        private List<String> errors;
        private String trace;
        private String path;

        public ErrorRequestBodyBuilder setTimestamp(Date timestamp) {
            this.timestamp = timestamp;

            return this;
        }

        public ErrorRequestBodyBuilder setStatus(HttpStatus status) {
            this.status = status.value();
            this.error = status.getReasonPhrase();

            return this;
        }

        public ErrorRequestBodyBuilder setException(Exception exception) {
            this.exception = exception.getClass().getName();

            return this;
        }

        public ErrorRequestBodyBuilder setMessage(String message) {
            this.message = message;

            return this;
        }

        public ErrorRequestBodyBuilder setErrors(List<String> errors) {
            this.errors = errors;

            return this;
        }

        public ErrorRequestBodyBuilder addError(String error) {
            if(errors == null)
                errors = new LinkedList<>();

            errors.add(error);

            return this;
        }

        public ErrorRequestBodyBuilder setTrace(Exception exception) {
            trace = exception.getStackTrace().toString();

            return this;
        }

        public ErrorRequestBodyBuilder setPath(String path) {
            this.path = path;

            return this;
        }

        public ErrorRequestBody build() {
            return new ErrorRequestBody(timestamp, status, error, exception, message, errors, trace, path);
        }
    }
}
