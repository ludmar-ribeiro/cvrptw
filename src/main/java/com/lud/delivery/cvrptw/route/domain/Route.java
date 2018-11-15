package com.lud.delivery.cvrptw.route.domain;

import java.time.LocalDateTime;

public interface Route {

    LocalDateTime getPickupTime();

    LocalDateTime getDeliveryTime();

    Location getOrigin();

    Location getDestiny();

}
