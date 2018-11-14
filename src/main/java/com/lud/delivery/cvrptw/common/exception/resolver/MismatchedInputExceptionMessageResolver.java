package com.lud.delivery.cvrptw.common.exception.resolver;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;

@Component
public class MismatchedInputExceptionMessageResolver extends AbstractResquestInputExceptionMessageResolver<MismatchedInputException> {

    @Override
    protected MessageSourceResolvable getMessageSourceResolvable(MismatchedInputException exception) {
        return new ExceptionMessageSourceResolvableWrapper(
                exception,
                getCodeSufix(exception),
                exception.getTargetType().getSimpleName(),
                getExpectedFormat(exception));
    }

    @Override
    protected Class<?> getRequiredType(MismatchedInputException exception) {
        return exception.getTargetType();
    }

}
