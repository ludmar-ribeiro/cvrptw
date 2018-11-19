package com.lud.delivery.cvrptw.common.exception;

/**
 * Exception thrown when a parsing fails 
 *
 * @author Ludmar Ribeiro
 *
 */
public class ResolvableParseException extends MessageSourceResolvableException {

    private static final long serialVersionUID = 8207612057208611559L;

    public ResolvableParseException(Object... args) {
        super(args);
    }
}
