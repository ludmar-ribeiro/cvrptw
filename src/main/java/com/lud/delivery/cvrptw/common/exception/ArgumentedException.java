package com.lud.delivery.cvrptw.common.exception;

public class ArgumentedException extends RuntimeException {

    private static final long serialVersionUID = 5433955013882987513L;

    private Object[] args;

    public ArgumentedException(Object... args) {
        super();
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }
}
