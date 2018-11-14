package com.lud.delivery.cvrptw.common.exception.resolver;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;

@Component
public class MethoArgumentNotValidExceptionMessageResolver extends AbstractMessageResolver<MethodArgumentNotValidException> {

    @Override
    protected MessageSourceResolvable getMessageSourceResolvable(MethodArgumentNotValidException exception) {
        return new ExceptionMessageSourceResolvableWrapper(exception, getCodeSufix(exception), exception.getBindingResult().getObjectName());
    }

}
