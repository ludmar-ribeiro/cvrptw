package com.lud.delivery.cvrptw.common.exception.resolver;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;

@Component
public class JsonParseExceptionMessageResolver extends AbstractMessageResolver<JsonParseException>{

    @Override
    protected MessageSourceResolvable getMessageSourceResolvable(JsonParseException exception) {
        String[] lines = exception.getMessage().split("\n");

        return new ExceptionMessageSourceResolvableWrapper(exception, getCodeSufix(exception), lines[0]);
    }

}
