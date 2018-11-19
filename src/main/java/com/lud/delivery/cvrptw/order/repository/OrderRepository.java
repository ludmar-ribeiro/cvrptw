package com.lud.delivery.cvrptw.order.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lud.delivery.cvrptw.order.domain.Order;

/**
 * The {@link Order} JPS repository
 *
 * @author Ludmar Ribeiro
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    @Query("SELECT o FROM Order o "
          +"WHERE o.restaurant.id = ?1 ")
    List<Order> searchByRestaurant(Integer restaurantId);

    @Query("SELECT o FROM Order o "
            +"WHERE o.restaurant.id = ?1 "
            +"AND o.deliveryTime > ?2 ")
    List<Order> searchByRestaurantAndDeliveryAfter(Integer restaurantId, LocalDateTime deliveryAfter);

    @Query("SELECT o FROM Order o "
            +"WHERE o.restaurant.id = ?1 "
            +"AND o.deliveryTime <= ?2 ")
    List<Order> searchByRestaurantAndDeliveryUntil(Integer restaurantId, LocalDateTime deliveryUntil);

    @Query("SELECT o FROM Order o "
            +"WHERE o.restaurant.id = ?1 "
            +"AND o.deliveryTime > ?2 "
            +"AND o.deliveryTime <= ?3 ")
    List<Order> searchByRestaurantAndDelivery(Integer restaurantId, LocalDateTime deliveryAfter, LocalDateTime deliveryUntil);

    @Query(value="SELECT * FROM PURCHASE_ORDER o "
               + "WHERE not exists(SELECT * "
               + "     FROM DELIVERY_ROUTE_ORDERS dro "
               + "     WHERE o.ID = dro.ORDERS_ID) "
               + "ORDER BY o.PICKUP_TIME, o.DELIVERY_TIME, o.ID ",
            nativeQuery=true)
    List<Order> getUndeliveredOrders();
}
