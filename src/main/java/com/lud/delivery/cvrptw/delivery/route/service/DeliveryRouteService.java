package com.lud.delivery.cvrptw.delivery.route.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lud.delivery.cvrptw.delivery.route.domain.DeliveryRoute;
import com.lud.delivery.cvrptw.delivery.route.provider.DeliveryRouteProvider;

/**
 * The {@link DeliveryRoute} service
 *
 * @author lribeiro
 *
 */
@Service
public class DeliveryRouteService {

    /**
     * The {@link DeliveryRoute} provider
     */
    @Autowired
    private DeliveryRouteProvider provider;

    /**
     * Get a {@link List} of {@link DeliveryRoute}
     *
     * @return {@link List} of {@link DeliveryRoute}
     */
    public List<DeliveryRoute> getAll() {
        return provider.getAll();
    }
}
