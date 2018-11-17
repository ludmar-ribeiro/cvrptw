package com.lud.delivery.cvrptw.common.comparator;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lud.delivery.cvrptw.common.comparator.rule.SortingRule;

public class RuledComparator<T> implements Comparator<T> {
    
    @Autowired
    private List<SortingRule<T>> sortingRules;

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
