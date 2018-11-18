package com.lud.delivery.cvrptw.route.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.lud.delivery.cvrptw.common.utils.DateTimeUtils;
import com.lud.delivery.cvrptw.route.calculator.FullRouteCalculator;
import com.lud.delivery.cvrptw.route.calculator.TravelTimeCalculator;
import com.lud.delivery.cvrptw.route.calculator.data.RouteCalculatorTestData;
import com.lud.delivery.cvrptw.route.domain.location.Location;
import com.lud.delivery.cvrptw.route.domain.route.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:route-calculator-time-window-free-test.properties")
public class RouteCalculatorTimeWindowFreeTest {

    @Autowired
    FullRouteCalculator calculator;

    @Autowired
    private RouteCalculatorTestData data;

    @Autowired TravelTimeCalculator travelTimeCalculator;

    @Test
    public void testCalculateOne() {
        CalculatedRoute route = calculator.calculate(Collections.singletonList(data.getOrderedRoutes().get(0)));

        assertNotNull(route);
        assertEquals(Double.valueOf(0), route.getLateDeliveryTime());
        assertEquals(Double.valueOf(3000000), route.getTravelTime());

        assertEquals(
                DateTimeUtils.ignoreSeconds(data.getCalculatedRoutes().get(0).getPickupTime()),
                DateTimeUtils.ignoreSeconds(route.getPickupTime()));

        assertEquals(
                DateTimeUtils.ignoreSeconds(route.getPickupTime()),
                DateTimeUtils.ignoreSeconds(data.getOrderedRoutes().get(0).getPickupTime()));

        assertEquals(
                DateTimeUtils.ignoreSeconds(data.getCalculatedRoutes().get(0).getDeliveryTime()),
                DateTimeUtils.ignoreSeconds(route.getDeliveryTime()));

        assertEquals(
                DateTimeUtils.ignoreSeconds(DateTimeUtils.addMilliseconds(
                        data.getOrderedRoutes().get(0).getPickupTime(),
                        route.getTravelTime())),
                DateTimeUtils.ignoreSeconds(data.getCalculatedRoutes().get(0).getDeliveryTime()));
    }

    @Test
    public void testCalculateFromSameRestaurant() {
        List<Route> routes = data.getOrderedRoutes()
                .stream()
                .limit(4)
                .collect(Collectors.toList());

        CalculatedRoute route = calculator.calculate(routes);

        assertNotNull(route);
        assertEquals(6, route.getLocations().size());
        assertEquals(4, route.getOrderedRoutes().size());
        assertEquals(Double.valueOf(0), route.getLateDeliveryTime());

        Double expectedTravelTime = calculateTravelTime(route.getLocations());

        assertEquals(expectedTravelTime, route.getTravelTime());

        assertEquals(
                DateTimeUtils.ignoreSeconds(DateTimeUtils.addMilliseconds(
                        route.getPickupTime(),
                        route.getTravelTime())),
                DateTimeUtils.ignoreSeconds(route.getDeliveryTime()));
    }

    @Test
    public void testCalculateDifferentRestaurants() {
        List<Route> routes = data.getOrderedRoutes()
                .stream()
                .limit(8)
                .collect(Collectors.toList());

        CalculatedRoute route = calculator.calculate(routes);

        assertNotNull(route);
        assertEquals(12, route.getLocations().size());
        assertEquals(8, route.getOrderedRoutes().size());
        assertEquals(Double.valueOf(0), route.getLateDeliveryTime());

        Double expectedTravelTime = calculateTravelTime(route.getLocations());

        assertEquals(expectedTravelTime, route.getTravelTime());

        assertEquals(
                DateTimeUtils.ignoreSeconds(DateTimeUtils.addMilliseconds(
                        route.getPickupTime(),
                        route.getTravelTime())),
                DateTimeUtils.ignoreSeconds(route.getDeliveryTime()));
    }

    private Double calculateTravelTime(List<Location> locations) {

        Double travelTime = Double.valueOf(0);
        Location origin = locations.get(0);

        for(int i=1; i < locations.size(); i++) {
            Location destiny = locations.get(i);

            travelTime += travelTimeCalculator.calculate(origin, destiny);

            origin = destiny;
        }

        return travelTime;
    }
}
