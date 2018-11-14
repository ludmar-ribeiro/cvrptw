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
    @SuppressWarnings("unchecked")
    public String resolveMessage(Exception exception) {

        Class<?> clazz = exception.getClass();
        while(clazz != null && !clazz.equals(Object.class)) {

            if(resolversMap.containsKey(clazz))
                return resolversMap.get(clazz).resolveMessage(exception);

            clazz = clazz.getSuperclass();
        }

        return null;
    }
}
