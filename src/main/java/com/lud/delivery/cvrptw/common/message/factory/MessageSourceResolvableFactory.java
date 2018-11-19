package com.lud.delivery.cvrptw.common.message.factory;

import org.springframework.context.MessageSourceResolvable;

/**
 * A factory for a {@link MessageSourceResolvable} from a object T
 * 
 * The {@link MessageSourceResolvable} is a interface used by the
 * Spring's localization engine
 *
 * @author Ludmar Ribeiro
 *
 * @param <T> a Class to be handled as {@link MessageSourceResolvable}
 */
public interface MessageSourceResolvableFactory<T> {

    /**
     * Returns a {@link MessageSourceResolvable} for the given object
     *
     * @param object
     * @return {@link MessageSourceResolvable} for the object
     */
    MessageSourceResolvable getMessageSourceResolvable(T object);

}
