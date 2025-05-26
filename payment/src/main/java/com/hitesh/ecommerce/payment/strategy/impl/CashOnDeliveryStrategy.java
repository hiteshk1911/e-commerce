package com.hitesh.ecommerce.payment.strategy.impl;

import com.hitesh.ecommerce.payment.dto.PaymentRequest;
import com.hitesh.ecommerce.payment.dto.PaymentResponse;
import com.hitesh.ecommerce.payment.strategy.PaymentStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CashOnDeliveryStrategy implements PaymentStrategy {
    @Override
    public PaymentResponse pay(PaymentRequest request) {
        log.info("Cash on Delivery selected. Marking as pending.");
        return PaymentResponse.builder()
                .status("PENDING")
                .transactionId(UUID.randomUUID().toString())
                .message("Will pay on delivery")
                .build();
    }
}

