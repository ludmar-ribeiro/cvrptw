package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

@Component
public class MessageSourceResolvableInvalidFormatExceptionFactory implements MessageSourceResolvableFactory<InvalidFormatException>{

    @Autowired
    private MessageSourceResolvableRequestInputRelatedExceptionFactory factory;


    @Override
    public MessageSourceResolvable getMessageSourceResolvable(InvalidFormatException exception) {
        return factory.getMessageSourceResolvable(
                exception,
                exception.getTargetType(),
                exception.getValue(),
                exception.getTargetType().getSimpleName());
    }
}
