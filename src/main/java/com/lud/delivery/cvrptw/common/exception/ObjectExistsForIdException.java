package com.lud.delivery.cvrptw.common.exception;

import com.lud.delivery.cvrptw.common.domain.Identifiable;

/**
 * Exception thrown when a {@link Identifiable} is already exists for an id 
 *
 * @author Ludmar Ribeiro
 *
 */
public class ObjectExistsForIdException extends MessageSourceResolvableException {

    private static final long serialVersionUID = -5113529483897218200L;

    public ObjectExistsForIdException(Identifiable<?> object) {
        super(object.getClass().getSimpleName().toLowerCase(), object.getId());
    }

}
