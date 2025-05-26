package com.hitesh.ecommerce.payment.dto;

import com.hitesh.ecommerce.payment.enums.PaymentMode;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
    private Long orderId;
    private BigDecimal amount;
    private PaymentMode paymentMode;
    private String details; // UPI ID, Card number (masked), etc.
}

