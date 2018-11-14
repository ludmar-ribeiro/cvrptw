package com.lud.delivery.cvrptw.common.exception.resolver;

public interface MessageResolver<T extends Exception> {

    String resolveMessage(T exception);
}
