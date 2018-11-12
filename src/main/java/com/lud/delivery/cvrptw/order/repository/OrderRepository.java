package com.lud.delivery.cvrptw.order.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lud.delivery.cvrptw.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    @Query("SELECT o FROM Order o "
          +"WHERE o.restaurant.id = ?1 ")
    List<Order> searchByRestaurant(Integer restaurantId);

    @Query("SELECT o FROM Order o "
            +"WHERE o.restaurant.id = ?1 "
            +"AND o.deliveryTime > ?2 ")
    List<Order> searchByRestaurantAndDeliveryAfter(Integer restaurantId, Date deliveryAfter);

    @Query("SELECT o FROM Order o "
            +"WHERE o.restaurant.id = ?1 "
            +"AND o.deliveryTime <= ?2 ")
    List<Order> searchByRestaurantAndDeliveryUntil(Integer restaurantId, Date deliveryUntil);

    @Query("SELECT o FROM Order o "
            +"WHERE o.restaurant.id = ?1 "
            +"AND o.deliveryTime > ?2 "
            +"AND o.deliveryTime <= ?3 ")
    List<Order> searchByRestaurantAndDelivery(Integer restaurantId, Date deliveryAfter, Date deliveryUntil);
}
