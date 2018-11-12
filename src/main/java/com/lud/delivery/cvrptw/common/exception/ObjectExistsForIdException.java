package com.lud.delivery.cvrptw.common.exception;

import com.lud.delivery.cvrptw.common.domain.Identifiable;

public class ObjectExistsForIdException extends ArgumentedException {

    private static final long serialVersionUID = -5113529483897218200L;

    public ObjectExistsForIdException(Identifiable<?> object) {
        super(object.getClass().getSimpleName().toLowerCase(), object.getId());
    }

}
