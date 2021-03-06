package com.lud.delivery.cvrptw.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;

/**
 * the {@link Restaurant} JPS repository
 *
 * @author Ludmar Ribeiro
 *
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
