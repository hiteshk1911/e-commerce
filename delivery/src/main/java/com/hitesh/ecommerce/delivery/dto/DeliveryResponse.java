package com.hitesh.ecommerce.delivery.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryResponse {
    private Long deliveryId;
    private String status;
    private String estimatedDelivery;
}

