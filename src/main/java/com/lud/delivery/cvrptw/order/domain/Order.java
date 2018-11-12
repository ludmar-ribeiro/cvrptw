package com.lud.delivery.cvrptw.order.domain;

import java.util.Date;

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

@Entity
@Table(name="PURCHASE_ORDER")
public class Order implements Identifiable<Integer>{

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
    private Date pickupTime;

    @NotNull
    @JsonProperty("delivery")
    private Date deliveryTime;

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

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

}
