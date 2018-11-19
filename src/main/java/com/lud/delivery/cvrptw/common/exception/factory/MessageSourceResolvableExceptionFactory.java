package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.exception.MessageSourceResolvableException;
import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

/**
 * A factory for a {@link MessageSourceResolvable} from a {@link MessageSourceResolvableException}
 * 
 * The {@link MessageSourceResolvable} is a interface used by the
 * Spring's localization engine
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class MessageSourceResolvableExceptionFactory implements MessageSourceResolvableFactory<MessageSourceResolvableException>{

    /**
     * Returns the exception itself because {@link MessageSourceResolvableException}
     * is a {@link MessageSourceResolvable} already
     *
     * @param exception
     */
    @Override
    public MessageSourceResolvable getMessageSourceResolvable(MessageSourceResolvableException exception) {
        return exception;
    }
}
