package com.lud.delivery.cvrptw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A micro-service to solve the problem known as
 * Capacitated Vehicle Routing Problem with Time Windows
 * (CVRPTW).
 * 
 * Application constructed using Spring Boot and Spring Cloud FWs.
 * 
 * @author Lud Ribeiro
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
