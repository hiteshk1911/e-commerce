package com.hitesh.ecommerce.payment.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private String status;
    private String transactionId;
    private String message;
}

