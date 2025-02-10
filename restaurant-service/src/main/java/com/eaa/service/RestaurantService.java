package com.eaa.service;

import com.eaa.dao.RestaurantOrderDAO;
import com.eaa.dto.OrderResponseDTO;
import com.eaa.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantOrderDAO orderDAO;

    public String greeting() {
        return "Welcome to Swiggy Restaurant service";
    }

    public OrderResponseDTO getOrder(String orderId) {
        OrderResponseDTO orderResponseDTO = orderDAO.getOrders(orderId);
        if (orderResponseDTO != null) {
            return orderResponseDTO;
        } else {
            throw new OrderNotFoundException("Order Not available with id :" + orderId);
        }
    }
}
