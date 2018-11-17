package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

@Component
public class MessageSourceResolvableMethodArgumentTypeMismatchExceptionFactory implements MessageSourceResolvableFactory<MethodArgumentTypeMismatchException> {

    @Autowired
    private MessageSourceResolvableRequestInputRelatedExceptionFactory factory;

    @Override
    public MessageSourceResolvable getMessageSourceResolvable(MethodArgumentTypeMismatchException exception) {
        return factory.getMessageSourceResolvable(
                exception,
                exception.getRequiredType(),
                exception.getName(),
                exception.getValue(),
                exception.getRequiredType().getSimpleName());
    }
}
