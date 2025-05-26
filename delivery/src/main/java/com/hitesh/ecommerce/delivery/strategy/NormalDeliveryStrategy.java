package com.hitesh.ecommerce.delivery.service.strategy;

import com.hitesh.ecommerce.delivery.entity.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component("NORMAL")
public class NormalDeliveryStrategy implements DeliveryStrategy {
    @Override
    public Delivery scheduleDelivery(Long orderId) {
        log.info("Scheduling NORMAL delivery for order {}", orderId);
        return Delivery.builder()
                .orderId(orderId)
                .deliveryType("NORMAL")
                .status("SCHEDULED")
                .estimatedDeliveryTime(LocalDateTime.now().plusDays(5))
                .build();
    }
}

