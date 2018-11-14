package com.lud.delivery.cvrptw.common.exception.resolver;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("rawtypes")
public class RootMessageResolver implements MessageResolver{

    private Map<Class<?>, MessageResolver> resolversMap;

    @Autowired
    public void setMessageResolvers(List<MessageResolver<?>> resolvers) {
        resolversMap = resolvers
                .stream()
                .collect(Collectors.toMap(r -> getGenericType(r), 
                        Function.identity()));
    }

    private Class<?> getGenericType(MessageResolver<?> messageResolver) {

        for(ResolvableType iface : ResolvableType.forInstance(messageResolver).getInterfaces()) {
            return iface.getGeneric(0).resolve();
        }

        return null;
    }

    @Override
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

    @SuppressWarnings("unchecked")
    private String resolveExceptionMessage(Exception exception) {

        Class<?> clazz = exception.getClass();

        while(clazz != null && !clazz.equals(Exception.class)) {

            if(resolversMap.containsKey(clazz))
                return resolversMap.get(clazz).resolveMessage(exception);

            clazz = clazz.getSuperclass();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private String resolveUnknownException(Exception exception) {
        if(resolversMap.containsKey(Exception.class))
            return resolversMap.get(Exception.class).resolveMessage(exception);

        return null;
    }
}
