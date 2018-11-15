package com.lud.delivery.cvrptw.exception;

import com.lud.delivery.cvrptw.common.exception.MessageSourceResolvableException;

public class ResolvableParseException extends MessageSourceResolvableException {

    private static final long serialVersionUID = 8207612057208611559L;

    public ResolvableParseException(Object... args) {
        super(args);
    }
}
