package com.hitesh.ecommerce.delivery.service.strategy;

import com.hitesh.ecommerce.delivery.entity.Delivery;

public interface DeliveryStrategy {
    Delivery scheduleDelivery(Long orderId);
}

