package com.lud.delivery.cvrptw.common.exception.resolver;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.exception.MessageSourceResolvableException;

@Component
public class MessageSourceResolvableExceptionMessageResolver implements MessageResolver<MessageSourceResolvableException>{

    @Autowired
    private MessageSource messageSource;

    @Override
    public String resolveMessage(MessageSourceResolvableException exception) {
        return messageSource.getMessage(exception, Locale.getDefault());
    }

}
