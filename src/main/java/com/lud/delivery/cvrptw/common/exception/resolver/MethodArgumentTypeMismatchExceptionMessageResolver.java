package com.lud.delivery.cvrptw.common.exception.resolver;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Component
public class MethodArgumentTypeMismatchExceptionMessageResolver implements MessageResolver<MethodArgumentTypeMismatchException> {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MessageSourceResolvableForExceptionFactory messageSourceResolvableFactory;

    @Override
    public String resolveMessage(MethodArgumentTypeMismatchException exception) {
        return  messageSource.getMessage(messageSourceResolvableFactory.create(exception), Locale.getDefault());
    }

}
