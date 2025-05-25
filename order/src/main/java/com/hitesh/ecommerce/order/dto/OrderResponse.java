package com.hitesh.ecommerce.order.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long orderId;
    private double finalPrice;
    private String message;
}

