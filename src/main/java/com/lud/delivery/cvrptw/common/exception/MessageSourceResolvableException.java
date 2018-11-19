package com.lud.delivery.cvrptw.common.exception;

import org.springframework.context.MessageSourceResolvable;

/**
 * A {@link MessageSourceResolvable} {@link Exception} to be reslved by the
 * Spring's localization engine.
 *
 * This message should be the super type any custom exception to facilitate its
 * message resolving
 *
 * @author Ludmar Ribeiro
 *
 */
public class MessageSourceResolvableException extends RuntimeException implements MessageSourceResolvable{

    private static final long serialVersionUID = 5433955013882987513L;

    private Object[] args;

    public MessageSourceResolvableException(Object... args) {
        super();
        this.args = args;
    }

    /**
     * Returns the arguments to be used by the localization engine
     *
     * @return an array of {@link Object}
     */
    @Override
    public Object[] getArguments() {
        return args;
    }

    /**
     * Returns the message codes
     *
     * @return an array of {@link String}
     */
    @Override
    public String[] getCodes() {
        return new String[]{
                this.getClass().getName(),
                this.getClass().getSimpleName()};
    }
}
