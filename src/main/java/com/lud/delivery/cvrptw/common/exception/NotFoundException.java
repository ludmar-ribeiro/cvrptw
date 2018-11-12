package com.lud.delivery.cvrptw.common.exception;

import java.text.MessageFormat;

import com.lud.delivery.cvrptw.common.domain.Identifiable;

public class NotFoundException extends Exception {

    private static final long serialVersionUID = -1034067372923274655L;

    private static final String MESSAGE = "There is no {0} registered for the id {1}";

    public NotFoundException(Identifiable<?> object) {
        super(getMessage(object.getClass().getName(), object.getId()));
    }

    public NotFoundException(Class<? extends Identifiable<?>> clazz, Object id) {
        super(getMessage(clazz.getName(), id));
    }

    private static String getMessage(String type, Object id) {
        return MessageFormat.format(MESSAGE, type.toLowerCase(), id);
    }
}
