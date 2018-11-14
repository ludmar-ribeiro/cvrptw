package com.lud.delivery.cvrptw.common.exception;

import org.springframework.context.MessageSourceResolvable;

public class MessageSourceResolvableException extends RuntimeException implements MessageSourceResolvable{

    private static final long serialVersionUID = 5433955013882987513L;

    private Object[] args;

    public MessageSourceResolvableException(Object... args) {
        super();
        this.args = args;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public String[] getCodes() {
        return new String[]{
                this.getClass().getName(),
                this.getClass().getSimpleName()};
    }
}
