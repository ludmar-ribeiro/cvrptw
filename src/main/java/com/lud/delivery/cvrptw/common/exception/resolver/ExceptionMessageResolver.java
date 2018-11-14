package com.lud.delivery.cvrptw.common.exception.resolver;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class ExceptionMessageResolver implements MessageResolver<Exception> {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MessageSourceResolvableForExceptionFactory messageSourceResolvableFactory;

    @Override
    public String resolveMessage(Exception exception) {
        return  messageSource.getMessage(messageSourceResolvableFactory.create(exception), Locale.getDefault());
    }

}
