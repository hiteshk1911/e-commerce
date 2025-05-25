package com.hitesh.ecommerce.order.controller;

import com.hitesh.ecommerce.order.dto.OrderRequest;
import com.hitesh.ecommerce.order.dto.OrderResponse;
import com.hitesh.ecommerce.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) {
        try {
            OrderResponse response = orderService.placeOrder(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Order failed: {}", e.getMessage());
            return ResponseEntity.status(500).body(OrderResponse.builder()
                    .message("Order failed: " + e.getMessage())
                    .build());
        }
    }
}

