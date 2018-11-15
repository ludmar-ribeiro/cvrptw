package com.lud.delivery.cvrptw.order.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lud.delivery.cvrptw.client.domain.Client;
import com.lud.delivery.cvrptw.client.json.ClientGetterDeserializer;
import com.lud.delivery.cvrptw.common.domain.Identifiable;
import com.lud.delivery.cvrptw.common.json.IdentifiableSerializer;
import com.lud.delivery.cvrptw.restaurant.domain.Restaurant;
import com.lud.delivery.cvrptw.restaurant.json.RestaurantGetterDeserializer;
import com.lud.delivery.cvrptw.route.domain.Location;
import com.lud.delivery.cvrptw.route.domain.Route;

@Entity
@Table(name="PURCHASE_ORDER")
public class Order implements Identifiable<Integer>, Route{

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

    @Override
    public Location getOrigin() {
        return restaurant;
    }

    @Override
    public Location getDestiny() {
        return client;
    }

}
