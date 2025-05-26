package com.hitesh.ecommerce.payment.model;

import com.hitesh.ecommerce.payment.enums.PaymentMode;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    private String transactionId;

    private String status;

    private LocalDateTime paidAt;
}

