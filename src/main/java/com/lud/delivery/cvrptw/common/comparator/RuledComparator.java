package com.lud.delivery.cvrptw.common.comparator;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lud.delivery.cvrptw.common.comparator.rule.SortingRule;

/**
 * A ruled comparator, compares two objects (T) by several {@link SortingRule}
 *
 * @author Ludmar Ribeiro
 *
 * @param <T> a Class to be compared
 */
public class RuledComparator<T> implements Comparator<T> {

    /**
     * The sorting rules
     *
     * NOTE: The order of the rules define its relevance make sure its order
     */
    @Autowired
    private List<SortingRule<T>> sortingRules;

    /**
     * Compares two objects according several {@link SortingRule}
     *
     * @param o1
     * @param o2
     */
    @Override
    public final int compare(T o1, T o2) {

        for(SortingRule<T> rule: sortingRules) {

            int result = rule.compare(o1, o2);

            if(result != 0)
                return result;
        }

        return 0;
    }
}
