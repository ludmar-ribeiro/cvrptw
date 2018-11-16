package com.lud.delivery.cvrptw.route.component;

import static org.junit.Assert.assertEquals;

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
import com.lud.delivery.cvrptw.route.component.data.RouteCalculatorTestData;
import com.lud.delivery.cvrptw.route.domain.CalculatedRoute;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.Route;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:route-calculator-test.properties")
public class RouteCalculatorTest {

    @Autowired
    RouteCalculator calculator;

    @Autowired
    private RouteCalculatorTestData data;

    @Autowired TravelTimeCalculator travelTimeCalculator;

    @Test
    public void testCalculateOne() {
        List<CalculatedRoute> results = calculator.calculate(Collections.singletonList(data.getOrderedRoutes().get(0)));

        assertEquals(1, results.size());

        assertEquals(
                DateTimeUtils.ignoreMilliseconds(data.getCalculatedRoutes().get(0).getPickupTime()),
                DateTimeUtils.ignoreMilliseconds(results.get(0).getPickupTime()));
        assertEquals(
                DateTimeUtils.ignoreMilliseconds(results.get(0).getPickupTime()),
                DateTimeUtils.ignoreMilliseconds(data.getOrderedRoutes().get(0).getPickupTime()));

        assertEquals(
                DateTimeUtils.ignoreMilliseconds(data.getCalculatedRoutes().get(0).getDeliveryTime()),
                DateTimeUtils.ignoreMilliseconds(results.get(0).getDeliveryTime()));
        assertEquals(
                DateTimeUtils.ignoreMilliseconds(data.getCalculatedRoutes().get(0).getDeliveryTime()),
                DateTimeUtils.ignoreMilliseconds(DateTimeUtils.addMilliseconds(
                        data.getOrderedRoutes().get(0).getPickupTime(),
                        results.get(0).getTravelTime())));
    }

    @Test
    public void testCalculateFromSameRestaurant() {
        List<Route> routes = data.getOrderedRoutes()
                .stream()
                .limit(4)
                .collect(Collectors.toList());

        List<CalculatedRoute> results = calculator.calculate(routes);

        assertEquals(results.size(), 1);
        assertEquals(results.get(0).getArc().size(), 6);
        assertEquals(results.get(0).getArc().stream().filter(l -> !l.isDepot()).count(), 4);

        Double expectedTravelTime = calculateTravelTime(results.get(0).getArc());
        assertEquals(expectedTravelTime, results.get(0).getTravelTime());
        assertEquals(
                DateTimeUtils.ignoreMilliseconds(results.get(0).getDeliveryTime()),
                DateTimeUtils.ignoreMilliseconds(DateTimeUtils.addMilliseconds(
                        results.get(0).getPickupTime(),
                        results.get(0).getTravelTime())));
    }

    @Test
    public void testCalculateDifferentRestaurants() {
        List<Route> routes = data.getOrderedRoutes()
                .stream()
                .limit(6)
                .collect(Collectors.toList());

        List<CalculatedRoute> results = calculator.calculate(routes);

        assertEquals(results.size(), 1);
        assertEquals(results.get(0).getArc().size(), 12);
        assertEquals(results.get(0).getArc().stream().filter(l -> !l.isDepot()).count(), 8);

        Double expectedTravelTime = calculateTravelTime(results.get(0).getArc());
        assertEquals(expectedTravelTime, results.get(0).getTravelTime());
        assertEquals(
                DateTimeUtils.ignoreMilliseconds(results.get(0).getDeliveryTime()),
                DateTimeUtils.ignoreMilliseconds(DateTimeUtils.addMilliseconds(
                        results.get(0).getPickupTime(),
                        results.get(0).getTravelTime())));
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
