package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

/**
 * A factory for a {@link MessageSourceResolvable} from a {@link InvalidFormatException}
 * 
 * The {@link MessageSourceResolvable} is a interface used by the
 * Spring's localization engine
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class MessageSourceResolvableInvalidFormatExceptionFactory implements MessageSourceResolvableFactory<InvalidFormatException>{

    /**
     * A common factory for all request input exceptions
     */
    @Autowired
    private MessageSourceResolvableRequestInputRelatedExceptionFactory factory;


    /**
     * Returns a {@link MessageSourceResolvable} for the given exception
     *
     * @param exception
     * @return {@link MessageSourceResolvable} for the exception
     */
    @Override
    public MessageSourceResolvable getMessageSourceResolvable(InvalidFormatException exception) {
        return factory.getMessageSourceResolvable(
                exception,
                exception.getTargetType(),
                exception.getValue(),
                exception.getTargetType().getSimpleName());
    }
}
