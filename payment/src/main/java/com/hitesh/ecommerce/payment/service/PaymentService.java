package com.hitesh.ecommerce.payment.service;

import com.hitesh.ecommerce.payment.dto.PaymentRequest;
import com.hitesh.ecommerce.payment.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
}

