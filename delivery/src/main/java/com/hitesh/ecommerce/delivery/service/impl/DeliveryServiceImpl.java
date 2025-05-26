package com.hitesh.ecommerce.delivery.service.impl;

import com.hitesh.ecommerce.delivery.dto.*;
import com.hitesh.ecommerce.delivery.entity.Delivery;
import com.hitesh.ecommerce.delivery.exception.DeliveryException;
import com.hitesh.ecommerce.delivery.repository.DeliveryRepository;
import com.hitesh.ecommerce.delivery.service.*;
import com.hitesh.ecommerce.delivery.service.strategy.DeliveryStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final Map<String, DeliveryStrategy> deliveryStrategies;

    @Override
    public DeliveryResponse createDelivery(DeliveryRequest request) {
        try {
            log.info("Processing delivery for order {}", request.getOrderId());
            DeliveryStrategy strategy = deliveryStrategies.get(request.getDeliveryType().toUpperCase());

            if (strategy == null) {
                throw new DeliveryException("Unsupported delivery type: " + request.getDeliveryType());
            }

            Delivery delivery = strategy.scheduleDelivery(request.getOrderId());
            Delivery saved = deliveryRepository.save(delivery);

            return DeliveryResponse.builder()
                    .deliveryId(saved.getId())
                    .status(saved.getStatus())
                    .estimatedDelivery(saved.getEstimatedDeliveryTime().toString())
                    .build();

        } catch (DeliveryException e) {
            log.warn("Delivery exception: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error in createDelivery", e);
            throw new DeliveryException("Internal error while processing delivery.");
        }
    }
}


