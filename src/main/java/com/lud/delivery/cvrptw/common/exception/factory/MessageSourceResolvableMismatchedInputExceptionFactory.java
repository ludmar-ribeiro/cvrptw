package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

@Component
public class MessageSourceResolvableMismatchedInputExceptionFactory implements MessageSourceResolvableFactory<MismatchedInputException> {

    @Autowired
    private MessageSourceResolvableRequestInputRelatedExceptionFactory factory;

    @Override
    public MessageSourceResolvable getMessageSourceResolvable(MismatchedInputException exception) {
        return factory.getMessageSourceResolvable(
                exception,
                exception.getTargetType(),
                exception.getTargetType().getSimpleName());
    }
}
