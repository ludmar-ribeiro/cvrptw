package com.lud.delivery.cvrptw.common.exception.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.lud.delivery.cvrptw.common.message.factory.MessageSourceResolvableFactory;

/**
 * A factory for a {@link MessageSourceResolvable} from a {@link MethodArgumentTypeMismatchException}
 * 
 * The {@link MessageSourceResolvable} is a interface used by the
 * Spring's localization engine
 *
 * @author Ludmar Ribeiro
 *
 */
@Component
public class MessageSourceResolvableMethodArgumentTypeMismatchExceptionFactory implements MessageSourceResolvableFactory<MethodArgumentTypeMismatchException> {

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
    public MessageSourceResolvable getMessageSourceResolvable(MethodArgumentTypeMismatchException exception) {
        return factory.getMessageSourceResolvable(
                exception,
                exception.getRequiredType(),
                exception.getName(),
                exception.getValue(),
                exception.getRequiredType().getSimpleName());
    }
}
