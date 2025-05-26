package com.hitesh.ecommerce.delivery.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequest {
    private Long orderId;
    private String deliveryType;
}

