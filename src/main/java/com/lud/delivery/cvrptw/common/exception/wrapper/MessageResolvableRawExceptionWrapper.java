package com.lud.delivery.cvrptw.common.exception.proxy;

import org.springframework.context.MessageSourceResolvable;

public class MessageResolvableRawExceptionProxy implements MessageSourceResolvable {

    Exception exception;
    private Object[] args;
    private String alternativeMessageCodeSufix="";

    public MessageResolvableRawExceptionProxy(Exception exception) {
        this.exception = exception;
    }

    public MessageResolvableRawExceptionProxy(Exception exception, Object... args) {
        this.exception = exception;
        this.args = args;
    }

    public MessageResolvableRawExceptionProxy(Exception exception, String alternativeMessageCodeSufix, Object[] args) {
        this.exception = exception;
        this.args = args;
        this.alternativeMessageCodeSufix = alternativeMessageCodeSufix;
    }

    @Override
    public String[] getCodes() {
        return new String[] {
                exception.getClass().getName().concat(alternativeMessageCodeSufix),
                exception.getClass().getSimpleName().concat(alternativeMessageCodeSufix),
                "common.exception.unknown"
        };
    }

    @Override
    public Object[] getArguments() {
        if(args != null)
            return args;

        return new Object[] {exception.getMessage()};
    }
}
