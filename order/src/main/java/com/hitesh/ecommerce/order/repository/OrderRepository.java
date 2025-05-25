package com.hitesh.ecommerce.order.repository;

import com.hitesh.ecommerce.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

