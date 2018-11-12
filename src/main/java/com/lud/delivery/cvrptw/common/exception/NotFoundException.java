package com.lud.delivery.cvrptw.common.exception;

import com.lud.delivery.cvrptw.common.domain.Identifiable;

public class NotFoundException extends ArgumentedException {

    private static final long serialVersionUID = -1034067372923274655L;

    public NotFoundException(Identifiable<?> object) {
        super(object.getClass().getSimpleName().toLowerCase(), object.getId());
    }

    public NotFoundException(Class<? extends Identifiable<?>> clazz, Object id) {
        super(clazz.getSimpleName().toLowerCase(), id);
    }
}
