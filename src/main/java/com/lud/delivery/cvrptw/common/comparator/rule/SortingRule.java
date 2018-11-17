package com.lud.delivery.cvrptw.common.comparator.rule;

public interface SortingRule<T> {
    int compare(T object1, T object2);
}
