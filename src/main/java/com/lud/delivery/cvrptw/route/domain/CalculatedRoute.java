package com.lud.delivery.cvrptw.route.domain;

import java.util.List;

public interface CalculatedRoute extends Route{

    Double getTravelTime();

    void setTravelTime(Double calculate);

    List<Location> getArc();

}
