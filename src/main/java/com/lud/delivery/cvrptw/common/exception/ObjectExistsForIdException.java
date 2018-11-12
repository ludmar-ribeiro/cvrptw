package com.lud.delivery.cvrptw.common.exception;

import java.text.MessageFormat;

import com.lud.delivery.cvrptw.common.domain.Identifiable;

public class ObjectExistsForIdException extends Exception {

    private static final long serialVersionUID = -5113529483897218200L;

    private static final String MESSAGE = "There is a(n) {0} registered for the id {1} already";

    public ObjectExistsForIdException(Identifiable<?> object) {
        super(MessageFormat.format(MESSAGE, object.getClass().getName().toLowerCase(), object.getId()));
    }

}
