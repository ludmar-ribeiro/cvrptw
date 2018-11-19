package com.lud.delivery.cvrptw.delivery.route.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lud.delivery.cvrptw.delivery.route.domain.DeliveryRoute;

/**
 * The {@link DeliveryRoute} JPS repository
 *
 * @author Ludmar Ribeiro
 *
 */
@Repository
public interface DeliveryRouteRepository extends JpaRepository<DeliveryRoute, Integer>{

}
