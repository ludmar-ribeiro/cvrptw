package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.exception.MessageSourceResolvableException;
import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

@Component
public class MessageSourceResolvableExceptionFactory implements MessageSourceResolvableFactory<MessageSourceResolvableException>{

    @Override
    public MessageSourceResolvable getMessageSourceResolvable(MessageSourceResolvableException exception) {
        return exception;
    }

}
