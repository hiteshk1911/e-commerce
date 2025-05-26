package com.hitesh.ecommerce.payment.repository;

import com.hitesh.ecommerce.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

