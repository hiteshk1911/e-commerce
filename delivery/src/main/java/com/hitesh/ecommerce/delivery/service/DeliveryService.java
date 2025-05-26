package com.hitesh.ecommerce.delivery.service;

import com.hitesh.ecommerce.delivery.dto.DeliveryRequest;
import com.hitesh.ecommerce.delivery.dto.DeliveryResponse;

public interface DeliveryService {
    DeliveryResponse createDelivery(DeliveryRequest request);
}

