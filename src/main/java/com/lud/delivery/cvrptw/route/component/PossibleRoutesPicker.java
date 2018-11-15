package com.lud.delivery.cvrptw.route.component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.Route;
import com.lud.delivery.cvrptw.route.domain.RouteWorkset;
import com.lud.delivery.cvrptw.route.domain.SynteticRoute;

@Component
public class PossibleRoutesPicker {

    @Autowired
    private SubRouteCalculator subRouteCalculator;

    public List<CalculatedRoute> pick(CalculatedRoute route, RouteWorkset workset) {
        List<Location> possibleTargets = pickPossibleTargets(route, workset);

        return possibleTargets
                .stream()
                .map(l -> calculateRouteFor(workset, route.getDestiny(), l))
            .collect(Collectors.toList());
    }

    private List<Location> pickPossibleTargets(CalculatedRoute route, RouteWorkset workset) {
        Location depot = workset.getDepot(route.getDestiny());

        return Stream.concat(
                    workset.getDepotToTargetMap().get(depot).stream(),
                    workset.getDepotToTargetMap().keySet().stream())
                .filter((l) -> isPossible(l, route, workset))
            .collect(Collectors.toList());
    }

    private boolean isPossible(Location location, CalculatedRoute route, RouteWorkset workset) {

        return !location.equals(route.getDestiny())
                && (
                        location.isDepot() && isPossibleDepot(location, route, workset) 
                     || !location.isDepot() && isPossibleTarget(location, route) 
                   );
    }

    private boolean isPossibleTarget(Location location, CalculatedRoute route) {
        return !route.getArc().contains(location);
    }

    private boolean isPossibleDepot(Location location, CalculatedRoute route, RouteWorkset workset) {
        List<Location> targetLocations = workset.getTargetsForDepot(location);

        return !route.getArc().containsAll(targetLocations);
    }

    private CalculatedRoute calculateRouteFor(RouteWorkset workset, Location origin, Location destiny) {
        return subRouteCalculator.calculate(getRouteFor(workset, origin, destiny));
    }

    private Route getRouteFor(RouteWorkset workset, Location origin, Location destiny) {
        LocalDateTime pickup = null;
        LocalDateTime delivery = null;

        if(!destiny.isDepot()) {
            CalculatedRoute orderedRoute = workset.getTargetToRouteMap().get(destiny);

            if(origin.isDepot() && origin.equals(orderedRoute.getOrigin()))
                return orderedRoute;

            pickup = orderedRoute.getPickupTime();
            delivery = orderedRoute.getDeliveryTime();
        }

        return new SynteticRoute(origin, destiny, pickup, delivery);
    }
}
