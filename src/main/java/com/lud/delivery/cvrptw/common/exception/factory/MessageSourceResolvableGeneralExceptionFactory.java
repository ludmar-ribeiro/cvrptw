package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;
import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

@Component
public class MessageSourceResolvableGeneralExceptionFactory implements MessageSourceResolvableFactory<Exception> {

    @Override
    public MessageSourceResolvable getMessageSourceResolvable(Exception exception) {
        return new ExceptionMessageSourceResolvableWrapper(exception, ".withOriginalMessage", exception.getMessage());
    }
}
