package com.lud.delivery.cvrptw.common.exception.resolver;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.exception.MessageSourceResolvableException;

@Component
public class MessageSourceResolvableExceptionMessageResolver extends AbstractMessageResolver<MessageSourceResolvableException>{

    @Override
    public MessageSourceResolvable getMessageSourceResolvable(MessageSourceResolvableException exception) {
        return exception;
    }

}
