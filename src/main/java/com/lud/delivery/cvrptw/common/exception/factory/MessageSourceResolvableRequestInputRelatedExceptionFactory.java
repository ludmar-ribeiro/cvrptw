package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;
import com.lud.delivery.cvrptw.common.utils.DefaultFormatSamplesProvider;

/**
 * A common factory for a {@link MessageSourceResolvable} from some
 * exceptions related to request input exceptions
 * 
 * The {@link MessageSourceResolvable} is a interface used by the
 * Spring's localization engine
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class MessageSourceResolvableRequestInputRelatedExceptionFactory {

    /**
     * A provider for formatting samples for types
     */
    @Autowired
    private DefaultFormatSamplesProvider defaultFormatSamplesProvider;

    /**
     * Generate a message code sufix if the exception's required type
     * has a formatting sample
     *
     * @param requiredType
     * @return {@link String}
     */
    protected String getCodeSufix(Class<?> requiredType) {
        return getExpectedFormat(requiredType) != null ? ".withFormat": null;
    }

    /**
     * The expected format sample for a requiredType
     *
     * @param requiredType
     * @return {@link String} or null
     */
    private String getExpectedFormat(Class<?> requiredType) {
        return defaultFormatSamplesProvider.getSample(requiredType);
    }

    /**
     * Returns a {@link MessageSourceResolvable} for the given exception
     *
     * @param exception
     * @param requiredType
     * @param args
     * @return {@link MessageSourceResolvable}
     */
    public MessageSourceResolvable getMessageSourceResolvable(Exception exception, Class<?> requiredType, Object... args) {
        return new ExceptionMessageSourceResolvableWrapper(
                exception,
                getCodeSufix(requiredType),
                includeToArgs(args, getExpectedFormat(requiredType)));
    }

    /**
     * Includes arguments to the agrs array
     *
     * @param args
     * @param include
     * @return an array of {@link Object} containing all the args  
     */
    private Object[] includeToArgs(Object[] args, Object... include) {
        Object[] array = new Object[args.length + include.length];

        System.arraycopy(args, 0, array, 0, args.length);
        System.arraycopy(include, 0, array, args.length, include.length);

        return array;
    }
}
