package com.hitesh.ecommerce.delivery.service.strategy;

import com.hitesh.ecommerce.delivery.entity.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component("PRIME")
public class PrimeDeliveryStrategy implements DeliveryStrategy {
    @Override
    public Delivery scheduleDelivery(Long orderId) {
        log.info("Scheduling PRIME delivery for order {}", orderId);
        return Delivery.builder()
                .orderId(orderId)
                .deliveryType("PRIME")
                .status("SCHEDULED")
                .estimatedDeliveryTime(LocalDateTime.now().plusDays(2))
                .build();
    }
}

