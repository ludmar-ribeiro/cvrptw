package com.lud.delivery.cvrptw.common.exception;

import com.lud.delivery.cvrptw.common.domain.Identifiable;

/**
 * Exception thrown when a {@link Identifiable} is not found 
 *
 * @author Ludmar Ribeiro
 *
 */
public class NotFoundException extends MessageSourceResolvableException {

    private static final long serialVersionUID = -1034067372923274655L;

    public NotFoundException(Identifiable<?> object) {
        super(object.getClass().getSimpleName().toLowerCase(), object.getId());
    }

    public NotFoundException(Class<? extends Identifiable<?>> clazz, Object id) {
        super(clazz.getSimpleName().toLowerCase(), id);
    }
}
