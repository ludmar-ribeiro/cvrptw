package com.lud.delivery.cvrptw.common.message.factory;

import org.springframework.context.MessageSourceResolvable;

public interface MessageSourceResolvableFactory<T> {

    MessageSourceResolvable getMessageSourceResolvable(T object);

}
