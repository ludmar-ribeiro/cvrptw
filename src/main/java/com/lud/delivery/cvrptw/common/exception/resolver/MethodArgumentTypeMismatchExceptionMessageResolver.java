package com.lud.delivery.cvrptw.common.exception.resolver;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;

@Component
public class MethodArgumentTypeMismatchExceptionMessageResolver extends AbstractResquestInputExceptionMessageResolver<MethodArgumentTypeMismatchException> {

    @Override
    protected MessageSourceResolvable getMessageSourceResolvable(MethodArgumentTypeMismatchException exception) {
        return new ExceptionMessageSourceResolvableWrapper(
                exception,
                getCodeSufix(exception),
                exception.getName(),
                exception.getValue(),
                exception.getRequiredType().getSimpleName(),
                getExpectedFormat(exception));
    }

    @Override
    protected Class<?> getRequiredType(MethodArgumentTypeMismatchException exception) {
        return exception.getRequiredType();
    }

}
