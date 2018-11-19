package com.lud.delivery.cvrptw.delivery.route.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lud.delivery.cvrptw.common.domain.Identifiable;
import com.lud.delivery.cvrptw.common.json.IdentifiableListSerializer;
import com.lud.delivery.cvrptw.order.domain.Order;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.route.domain.location.Location;
import com.lud.delivery.cvrptw.route.domain.route.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.route.SubRoute;

/**
 * The delivery route POJO
 *
 * @author Ludmar Ribeiro
 *
 */
@Entity
@JsonRootName("routes")
public class DeliveryRoute implements SubRoute, Identifiable<Integer> {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany
    @JsonSerialize(using=IdentifiableListSerializer.class)
    private List<Order> orders = new LinkedList<>();

    @Override
    public Integer getId() {
        return id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public Location getDepot() {
        return restaurant;
    }

    @JsonIgnore
    @Override
    public List<OrderedRoute> getOrderedRoutes() {
        return Collections.unmodifiableList(orders);
    }

    @Override
    public void addOrderedRoute(OrderedRoute orderedRoute) {
        orders.add((Order) orderedRoute);
    }
}
