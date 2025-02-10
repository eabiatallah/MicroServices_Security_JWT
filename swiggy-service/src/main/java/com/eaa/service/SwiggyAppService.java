package com.eaa.service;

import com.eaa.client.RestaurantServiceClient;
import com.eaa.dto.OrderResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SwiggyAppService {

    @Autowired
    private RestaurantServiceClient restaurantServiceClient;

    public String greeting() {
        return "Welcome to Swiggy App Service";
    }


    public OrderResponseDTO checkOrderStatus(String orderId) throws Exception {
        return restaurantServiceClient.fetchOrderStatus(orderId);
    }
}
