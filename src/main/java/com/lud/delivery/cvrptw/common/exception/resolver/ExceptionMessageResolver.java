package com.lud.delivery.cvrptw.common.exception.resolver;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.exception.proxy.MessageResolvableRawExceptionProxy;

@Component
public class ExceptionMessageResolver implements MessageResolver<Exception> {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String resolveMessage(Exception exception) {
        return messageSource.getMessage(new MessageResolvableRawExceptionProxy(exception), Locale.getDefault());
    }

}
