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

/**
 * Test for the route calculation logic
 * 
 * This test cover cases free from the Time Window Restriction
 * 
 * @author Ludmar Ribeiro
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:route-calculator-time-window-free-test.properties")
public class RouteCalculatorTimeWindowFreeTest {

    /**
     * Test subject
     *
     * The Route Calculation component 
     */
    @Autowired
    FullRouteCalculator calculator;

    /**
     * Test data
     */
    @Autowired
    private RouteCalculatorTestData data;

    /**
     * Travel time calculator component
     *
     * Used to generate comparison values 
     */
    @Autowired TravelTimeCalculator travelTimeCalculator;

    /**
     * Test the calculation for only one order
     *
     * Simplest test possible
     *
     * The execution should return one route with the travel time
     * from the restaurant R[0] to the client C[0] 
     */
    @Test
    public void testCalculateOne() {
        CalculatedRoute route = calculator.calculate(Collections.singletonList(data.getOrderedRoutes().get(0)));

        assertNotNull(route);

        //This test data should not generate late deliveries
        assertEquals(Double.valueOf(0), route.getLateDeliveryTime());

        //This execution should to generate a travel time equal to 50 minutes  
        assertEquals(Double.valueOf(3000000), route.getTravelTime());

        //The pickup time of the execution should be the same from the test data
        assertEquals(
                DateTimeUtils.ignoreSeconds(data.getCalculatedRoutes().get(0).getPickupTime()),
                DateTimeUtils.ignoreSeconds(route.getPickupTime()));

        //The pickup time of the execution should be the same from the test data
        assertEquals(
                DateTimeUtils.ignoreSeconds(route.getPickupTime()),
                DateTimeUtils.ignoreSeconds(data.getOrderedRoutes().get(0).getPickupTime()));

        //The delivery time of the execution should be the same from the test data
        assertEquals(
                DateTimeUtils.ignoreSeconds(data.getCalculatedRoutes().get(0).getDeliveryTime()),
                DateTimeUtils.ignoreSeconds(route.getDeliveryTime()));

        //The delivery time of the execution should be the pickup time plus travel time
        assertEquals(
                DateTimeUtils.ignoreSeconds(DateTimeUtils.addMilliseconds(
                        data.getOrderedRoutes().get(0).getPickupTime(),
                        route.getTravelTime())),
                DateTimeUtils.ignoreSeconds(data.getCalculatedRoutes().get(0).getDeliveryTime()));
    }

    /**
     * Test the calculation for orders from the same restaurant
     *
     * The execution should return one route with the travel time
     * for a route that delivers the orders from the restaurant R[0]
     * to the clients C[0], C[1], C[2] and C[3]
     */
    @Test
    public void testCalculateFromSameRestaurant() {
        List<Route> routes = data.getOrderedRoutes()
                .stream()
                .limit(4)
                .collect(Collectors.toList());

        CalculatedRoute route = calculator.calculate(routes);

        assertNotNull(route);

        //This execution pass by 6 locations 4 clients and the restaurant 2 times 
        assertEquals(6, route.getLocations().size());

        //This execution should cover 4 orders delivery
        assertEquals(4, route.getOrderedRoutes().size());

        //This test data should not generate late deliveries
        assertEquals(Double.valueOf(0), route.getLateDeliveryTime());

        Double expectedTravelTime = calculateTravelTime(route.getLocations());

        //The travel time of this execution should be the sum of the travel time
        //between each location that route had passed by
        assertEquals(expectedTravelTime, route.getTravelTime());

        //The delivery time of the execution should be the first pickup time 
        //plus travel time
        assertEquals(
                DateTimeUtils.ignoreSeconds(DateTimeUtils.addMilliseconds(
                        route.getPickupTime(),
                        route.getTravelTime())),
                DateTimeUtils.ignoreSeconds(route.getDeliveryTime()));
    }

    /**
     * Test the calculation for orders from two different restaurants
     *
     * The execution should return one route with the travel time
     * for a route that delivers the orders from the restaurants R[0] and R[1]
     * to the clients C[0], C[1], C[2], C[3], C[4], C{5], C[6] and C[7]
     */
    @Test
    public void testCalculateDifferentRestaurants() {
        List<Route> routes = data.getOrderedRoutes()
                .stream()
                .limit(8)
                .collect(Collectors.toList());

        CalculatedRoute route = calculator.calculate(routes);

        assertNotNull(route);

        //This execution pass by 12 locations 8 clients 
        //and two restaurants 2 times each one 
        assertEquals(12, route.getLocations().size());

        //This execution should cover 8 orders delivery
        assertEquals(8, route.getOrderedRoutes().size());

        //This test data should not generate late deliveries
        assertEquals(Double.valueOf(0), route.getLateDeliveryTime());

        Double expectedTravelTime = calculateTravelTime(route.getLocations());

        //The travel time of this execution should be the sum of the travel time
        //between each location that route had passed by
        assertEquals(expectedTravelTime, route.getTravelTime());

        //The delivery time of the execution should be the first pickup time 
        //plus travel time
        assertEquals(
                DateTimeUtils.ignoreSeconds(DateTimeUtils.addMilliseconds(
                        route.getPickupTime(),
                        route.getTravelTime())),
                DateTimeUtils.ignoreSeconds(route.getDeliveryTime()));
    }

    /**
     * The calculation of the travel time between each location of a route
     *
     * This method is used for comparison with the tests results
     * 
     * @param locations
     * @return the sum of travel time between locations of the route  
     */
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
