package com.hitesh.ecommerce.payment.service.impl;

import com.hitesh.ecommerce.payment.dto.PaymentRequest;
import com.hitesh.ecommerce.payment.dto.PaymentResponse;
import com.hitesh.ecommerce.payment.model.Payment;
import com.hitesh.ecommerce.payment.repository.PaymentRepository;
import com.hitesh.ecommerce.payment.service.PaymentService;
import com.hitesh.ecommerce.payment.strategy.PaymentStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyFactory paymentStrategyFactory;

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        try {
            var strategy = paymentStrategyFactory.getStrategy(request.getPaymentMode());
            var response = strategy.pay(request);

            Payment payment = Payment.builder()
                    .orderId(request.getOrderId())
                    .amount(request.getAmount())
                    .paymentMode(request.getPaymentMode())
                    .transactionId(response.getTransactionId())
                    .status(response.getStatus())
                    .paidAt(LocalDateTime.now())
                    .build();

            paymentRepository.save(payment);
            return response;
        } catch (Exception ex) {
            log.error("Payment processing failed for order: {}", request.getOrderId(), ex);
            return PaymentResponse.builder()
                    .status("FAILURE")
                    .transactionId(null)
                    .message("Payment failed: " + ex.getMessage())
                    .build();
        }
    }
}

