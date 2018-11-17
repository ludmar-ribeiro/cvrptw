package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;
import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

@Component
public class MessageSourceResolvableMethodArgumentNotValidExceptionFactory implements MessageSourceResolvableFactory<MethodArgumentNotValidException> {

    @Override
    public MessageSourceResolvable getMessageSourceResolvable(MethodArgumentNotValidException exception) {
        return new ExceptionMessageSourceResolvableWrapper(exception, null, exception.getBindingResult().getObjectName());
    }

}
