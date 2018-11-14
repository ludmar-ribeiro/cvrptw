package com.lud.delivery.cvrptw.common.exception.resolver;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;

@Component
public class InvalidFormatExceptionMessageResolver extends AbstractResquestInputExceptionMessageResolver<InvalidFormatException>{

    @Override
    protected MessageSourceResolvable getMessageSourceResolvable(InvalidFormatException exception) {
        return new ExceptionMessageSourceResolvableWrapper(
                exception,
                getCodeSufix(exception),
                exception.getValue(),
                exception.getTargetType().getSimpleName(),
                getExpectedFormat(exception));
    }

    @Override
    protected Class<?> getRequiredType(InvalidFormatException exception) {
        return exception.getTargetType();
    }


}
