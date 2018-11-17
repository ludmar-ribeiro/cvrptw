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

@Component
@SuppressWarnings("rawtypes")
public class MessageResolver {

    private Map<Class<?>, MessageSourceResolvableFactory> factoriesMap;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    public void setMessageResolvers(List<MessageSourceResolvableFactory<?>> factories) {
        factoriesMap = factories
                .stream()
                .collect(Collectors.toMap(r -> getGenericType(r), 
                        Function.identity()));
    }

    private Class<?> getGenericType(MessageSourceResolvableFactory<?> factory) {
        return ResolvableType.forInstance(factory)
                .getInterfaces()[0]
                .getGeneric(0)
                .resolve();
    }

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

    private String resolveExceptionMessage(Exception exception) {

        Class<?> clazz = exception.getClass();

        while(clazz != null && !clazz.equals(Exception.class)) {

            if(factoriesMap.containsKey(clazz))
                return resolveExceptionMessage(clazz, exception);

            clazz = clazz.getSuperclass();
        }

        return null;
    }

    private String resolveUnknownException(Exception exception) {
        return resolveExceptionMessage(Exception.class, exception);
    }

    @SuppressWarnings("unchecked")
    private String resolveExceptionMessage(Class<?> clazz, Exception exception) {
        if(factoriesMap.containsKey(clazz))
            return resolveMessage(factoriesMap.get(clazz).getMessageSourceResolvable(exception));

        return null;
    }

    private String resolveMessage(MessageSourceResolvable messageSourceResolvable) {
        return messageSource.getMessage(messageSourceResolvable, Locale.getDefault());
    }
}
