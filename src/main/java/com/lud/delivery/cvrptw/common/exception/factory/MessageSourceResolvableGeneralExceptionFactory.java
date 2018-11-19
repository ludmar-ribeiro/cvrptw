package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.common.exception.wrapper.ExceptionMessageSourceResolvableWrapper;
import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

/**
 * A factory for a {@link MessageSourceResolvable} from a {@link Exception}
 * 
 * The {@link MessageSourceResolvable} is a interface used by the
 * Spring's localization engine
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class MessageSourceResolvableGeneralExceptionFactory implements MessageSourceResolvableFactory<Exception> {

     /**
      * Returns a {@link MessageSourceResolvable} for the given exception
      *
      * @param exception
      * @return {@link MessageSourceResolvable} for the exception
      */
     @Override
    public MessageSourceResolvable getMessageSourceResolvable(Exception exception) {
        return new ExceptionMessageSourceResolvableWrapper(exception, ".withOriginalMessage", exception.getMessage());
    }
}
