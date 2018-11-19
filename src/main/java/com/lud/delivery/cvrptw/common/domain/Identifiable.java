package com.lud.delivery.cvrptw.common.domain;

/**
 * A interface for objects that is identifiable by a id of the type T
 *
 * @author Ludmar Ribeiro
 *
 * @param <T> the id type
 */
public interface Identifiable<T> {

    T getId();

}
