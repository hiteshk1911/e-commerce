package com.hitesh.ecommerce.payment.strategy;

import com.hitesh.ecommerce.payment.dto.PaymentRequest;
import com.hitesh.ecommerce.payment.dto.PaymentResponse;

public interface PaymentStrategy {
    PaymentResponse pay(PaymentRequest request);
}

