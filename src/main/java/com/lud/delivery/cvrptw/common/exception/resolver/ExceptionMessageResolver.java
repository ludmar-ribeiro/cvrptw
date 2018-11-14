package com.lud.delivery.cvrptw.common.exception.resolver;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;

@Component
public class ExceptionMessageResolver extends AbstractMessageResolver<Exception> {

    @Override
    protected MessageSourceResolvable getMessageSourceResolvable(Exception exception) {
        return new ExceptionMessageSourceResolvableWrapper(exception, getCodeSufix(exception), exception.getMessage());
    }

    @Override
    protected String getCodeSufix(Exception exception) {
        return ".withOriginalMessage";
    }
}
