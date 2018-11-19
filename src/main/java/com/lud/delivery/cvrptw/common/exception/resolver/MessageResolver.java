package com.lud.delivery.cvrptw.common.exception.resolver;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

/**
 * The message resolver for the exceptions caught
 *
 * This component uses some {@link MessageSourceResolvableFactory}
 * to resolve the message for exceptions using the Sppring's
 * localization engine
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
@SuppressWarnings("rawtypes")
public class MessageResolver {

    /**
     * {@link Map} of {@link MessageSourceResolvableFactory} by its related type
     */
    private Map<Class<?>, MessageSourceResolvableFactory> factoriesMap;

    /**
     * The Springs's localization engine
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Method used to inject the list of {@link MessageSourceResolvableFactory} and
     * transform it to a {@link Map} of {@link MessageSourceResolvableFactory} 
     * by its related type
     *
     * @param factories
     */
    @Autowired
    public void setMessageResolvers(List<MessageSourceResolvableFactory<?>> factories) {
        factoriesMap = factories
                .stream()
                .collect(Collectors.toMap(r -> getGenericType(r), 
                        Function.identity()));
    }

    /**
     * Returns a related type of a {@link MessageSourceResolvableFactory}
     *
     * @param factory
     * @return {@link Class} the related type
     */
    private Class<?> getGenericType(MessageSourceResolvableFactory<?> factory) {
        return ResolvableType.forInstance(factory)
                .getInterfaces()[0]
                .getGeneric(0)
                .resolve();
    }

    /**
     * Resolves the message for a given exception
     *
     * @param exception
     * @return {@link String}
     */
    public String resolveMessage(Exception exception) {

        Exception ex = exception;

        while(ex != null) {

            String message = resolveExceptionMessage(ex);

            if(message != null)
                return message;

            if(ex.getCause() != null && ex.getCause() instanceof Exception) {
                ex = (Exception) ex.getCause();
                continue;
            }

            ex = null;
        }

        return resolveUnknownException(exception);
    }

   /**
    * Resolves the message for a given exception
    *
    * @param exception
    * @return {@link String}
    */
    private String resolveExceptionMessage(Exception exception) {

        Class<?> clazz = exception.getClass();

        while(clazz != null && !clazz.equals(Exception.class)) {

            if(factoriesMap.containsKey(clazz))
                return resolveExceptionMessage(clazz, exception);

            clazz = clazz.getSuperclass();
        }

        return null;
    }

   /**
    * Resolves the message for a given exception 
    * with a default {@link MessageSourceResolvableFactory}
    * if the exception type is unknown 
    *
    * @param exception
    * @return {@link String}
    */
    private String resolveUnknownException(Exception exception) {
        return resolveExceptionMessage(Exception.class, exception);
    }

    /**
     * Resolves the message for a given exception 
     * with the {@link MessageSourceResolvableFactory}
     * related to the type clazz 
     *
     * @param exception
     * @return {@link String}
     */
    @SuppressWarnings("unchecked")
    private String resolveExceptionMessage(Class<?> clazz, Exception exception) {
        if(factoriesMap.containsKey(clazz))
            return resolveMessage(factoriesMap.get(clazz).getMessageSourceResolvable(exception));

        return null;
    }

    /**
     * Resolves the message for a given {@link MessageSourceResolvable} 
     *
     * @param messageSourceResolvable
     * @return {@link String}
     */
    private String resolveMessage(MessageSourceResolvable messageSourceResolvable) {
        return messageSource.getMessage(messageSourceResolvable, Locale.getDefault());
    }
}
