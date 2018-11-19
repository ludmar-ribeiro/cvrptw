package com.lud.delivery.cvrptw.route.evaluator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.workset.RouteCalculationWorkset;
import com.lud.delivery.cvrptw.route.evaluator.rule.StopRule;

/**
 * Stop evaluator
 *
 * Component that verify if a calculation process is done
 *
 * This component uses a {@link List} of {@link StopRule} to 
 * verify if a calculation process is done, the process will
 * be considered done if any of the {@link StopRule}
 * returns true
 *
 * @author lribeiro
 *
 */
@Component
public class StopEvaluator {

    /**
     * {@link List} of {@link StopRule}
     */
    @Autowired
    private List<StopRule> stopRules;

    /**
     * Verify if a calculation process is done.
     *
     * @param workset
     * @param route
     * @return boolean, true when the calculation process is done
     */
    public boolean shouldStop(RouteCalculationWorkset workset, CalculatedRoute route) {
        return stopRules
                .stream()
                    .map(r -> r.shouldStop(workset, route))
                    .reduce((b1, b2) -> b1 || b2)
                .orElse(false);
    }
}
