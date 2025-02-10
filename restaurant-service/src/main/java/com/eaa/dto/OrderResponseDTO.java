package com.eaa.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private String orderId;
    private String name;
    private int qty;
    private double price;
    private Date orderDate;
    private String status;
    private int estimateDeliveryWindow;
}
