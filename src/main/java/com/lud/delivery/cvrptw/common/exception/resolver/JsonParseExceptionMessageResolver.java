package com.lud.delivery.cvrptw.common.exception.resolver;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;

@Component
public class JsonParseExceptionMessageResolver implements MessageResolver<JsonParseException>{

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MessageSourceResolvableForExceptionFactory messageSourceResolvableFactory;

    @Override
    public String resolveMessage(JsonParseException exception) {
        return  messageSource.getMessage(messageSourceResolvableFactory.create(exception), Locale.getDefault());
    }

}
