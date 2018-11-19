package com.lud.delivery.cvrptw.order.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.client.json.ClientGetterDeserializer;
import com.lud.delivery.cvrptw.common.domain.Identifiable;
import com.lud.delivery.cvrptw.common.json.IdentifiableSerializer;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.restaurant.json.RestaurantGetterDeserializer;
import com.lud.delivery.cvrptw.route.domain.location.Location;
import com.lud.delivery.cvrptw.route.domain.route.OrderedRoute;
import com.lud.delivery.cvrptw.route.domain.route.Route;

/**
 * The POJO for Order
 *
 * @author Ludmar Ribeiro
 *
 */
@Entity
@Table(name="PURCHASE_ORDER")
public class Order implements Identifiable<Integer>, OrderedRoute{

    @Id
    @NotNull
    private Integer id;

    @NotNull
    @ManyToOne
    @JsonProperty("restaurantId")
    @JsonDeserialize(using=RestaurantGetterDeserializer.class)
    @JsonSerialize(using=IdentifiableSerializer.class)
    private Restaurant restaurant;

    @NotNull
    @ManyToOne
    @JsonProperty("clientId")
    @JsonDeserialize(using=ClientGetterDeserializer.class)
    @JsonSerialize(using=IdentifiableSerializer.class)
    private Client client;

    @NotNull
    @JsonProperty("pickup")
    private LocalDateTime pickupTime;

    @NotNull
    @JsonProperty("delivery")
    private LocalDateTime deliveryTime;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    @Override
    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @JsonIgnore
    @Override
    public Location getOrigin() {
        return restaurant;
    }

    @JsonIgnore
    @Override
    public Location getDestiny() {
        return client;
    }

    @Override
    public int hashCode() {
        return routeHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof Route))
            return false;

        Route otherRoute = (Route) obj;

        return this.equalsRoute(otherRoute);
    }
}
