package com.lud.delivery.cvrptw.common.comparator.rule;

import com.lud.delivery.cvrptw.common.comparator.RuledComparator;

/**
 * A comparison rule to be used be a {@link RuledComparator}
 *
 * @author Ludmar Ribeiro
 *
 * @param <T> a Class to be compared 
 */
public interface SortingRule<T> {

    /**
     * Compares two objects according this {@link SortingRule}
     *
     * @param o1
     * @param o2
     */
    int compare(T object1, T object2);
}
