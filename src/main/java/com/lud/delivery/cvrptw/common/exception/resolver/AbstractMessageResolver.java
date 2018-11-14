package com.lud.delivery.cvrptw.common.exception.resolver;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;

public abstract class AbstractMessageResolver<T extends Exception> implements MessageResolver<T>{

    @Autowired
    private MessageSource messageSource;

    @Override
    public String resolveMessage(T exception) {
        return messageSource.getMessage(getMessageSourceResolvable(exception), Locale.getDefault());
    }

    protected abstract MessageSourceResolvable getMessageSourceResolvable(T exception);

    protected String getCodeSufix(T exception) {
        return null;
    }
}
