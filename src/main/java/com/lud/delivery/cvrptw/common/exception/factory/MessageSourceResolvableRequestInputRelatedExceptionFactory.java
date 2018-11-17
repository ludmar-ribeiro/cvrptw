package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;
import com.lud.delivery.cvrptw.common.utils.DefaultFormatSamplesProvider;

@Component
public class MessageSourceResolvableRequestInputRelatedExceptionFactory {

    @Autowired
    private DefaultFormatSamplesProvider defaultFormatSamplesProvider;

    protected String getCodeSufix(Class<?> requiredType) {
        return getExpectedFormat(requiredType) != null ? ".withFormat": null;
    }

    private String getExpectedFormat(Class<?> requiredType) {
        return defaultFormatSamplesProvider.getSample(requiredType);
    }

    public MessageSourceResolvable getMessageSourceResolvable(Exception exception, Class<?> requiredType, Object... args) {
        return new ExceptionMessageSourceResolvableWrapper(
                exception,
                getCodeSufix(requiredType),
                includeToArgs(args, getExpectedFormat(requiredType)));
    }

    private Object[] includeToArgs(Object[] args, Object... include) {
        Object[] array = new Object[args.length + include.length];

        System.arraycopy(args, 0, array, 0, args.length);
        System.arraycopy(include, 0, array, args.length, include.length);

        return array;
    }
}
