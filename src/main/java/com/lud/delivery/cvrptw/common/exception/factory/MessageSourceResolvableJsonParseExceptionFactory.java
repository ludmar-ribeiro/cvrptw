package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;
import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

@Component
public class MessageSourceResolvableJsonParseExceptionFactory implements MessageSourceResolvableFactory<JsonParseException>{

    @Override
    public MessageSourceResolvable getMessageSourceResolvable(JsonParseException exception) {
        String[] lines = exception.getMessage().split("\n");

        return new ExceptionMessageSourceResolvableWrapper(exception, null, lines[0]);
    }
}
