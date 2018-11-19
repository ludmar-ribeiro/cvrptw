package com.lud.delivery.cvrptw.route.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.lud.delivery.cvrptw.route.calculator.TravelTimeCalculator;
import com.lud.delivery.cvrptw.route.calculator.data.TravelTimeCalculateTestData;
import com.lud.delivery.cvrptw.route.domain.location.Location;

/**
 * Test for the travel time calculation logic
 *
 * @author Ludmar Ribeiro
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:travel-time-calculator-test.properties")
public class TravelTimeCalculatorTest {

    /**
     * Test data
     */
    @Autowired
    private TravelTimeCalculateTestData data;

    /**
     * Test subject.
     *
     *  The travel time calculation component.
     */
    @Autowired
    private TravelTimeCalculator calculator;


    /**
     * Test to verify the distance calculation logic.
     *
     *
     * This test verify all controlled cases from the test data.
     *
     */
    @Test
    public void testCalculateDistance() {
        for(int i = 0; i< data.getOrigins().size(); i++) {
            Location origin = data.getOrigins().get(i);
            Location destiny = data.getDestinies().get(i);

            Double calculatedDistance = calculator.calculateDistance(origin, destiny);
            Double expectedDistance = data.getDistances().get(i);

            assertEquals(expectedDistance, calculatedDistance );
        }
    }

    /**
     * Test to verify the distance calculation logic.
     *
     *
     * This test verify all uncontrolled cases from the test data.
     *
     */
    @Test
    public void testCalculateDistanceRandomValues() {
        for(int i = 0; i < data.getRandomOrigins().size(); i++ )
        {
            Location origin = data.getRandomOrigins().get(i);
            Location destiny = data.getRandomDestinies().get(i);

            Double calculatedDistance = calculator.calculateDistance(origin, destiny);
            Double expectedDistance = data.getRandomDistance(i);

            assertEquals(expectedDistance, calculatedDistance);
        }
    }

    /**
     * Test to verify the travel time calculation logic.
     *
     *
     * This test verify all controlled cases from the test data.
     *
     */

    @Test
    public void testCalculate() {
        for(int i = 0; i< data.getOrigins().size(); i++) {
            Location origin = data.getOrigins().get(i);
            Location destiny = data.getDestinies().get(i);

            Double calculatedTravelTime = calculator.calculate(origin, destiny);
            Double expectedTravelTime = data.getTravelTimes().get(i);

            assertEquals(expectedTravelTime, calculatedTravelTime);
        }
    }

    /**
     * Test to verify the travel time calculation logic.
     *
     *
     * This test verify all uncontrolled cases from the test data.
     *
     */

    @Test
    public void testCalculateRandomValues() {
        for(int i = 0; i < data.getRandomOrigins().size(); i++ ) {
            Location origin = data.getRandomOrigins().get(i);
            Location destiny = data.getRandomDestinies().get(i);

            Double calculatedTravelTime = calculator.calculate(origin, destiny);
            Double expectedTravelTime = data.getRandomTravelTime(i);

            assertEquals(expectedTravelTime, calculatedTravelTime);
        }
    }
}