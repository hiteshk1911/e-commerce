package com.hitesh.ecommerce.delivery.repository;

import com.hitesh.ecommerce.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}

