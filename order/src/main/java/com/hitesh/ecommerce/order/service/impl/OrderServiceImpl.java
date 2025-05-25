package com.hitesh.ecommerce.order.service.impl;

import com.hitesh.ecommerce.order.dto.OrderRequest;
import com.hitesh.ecommerce.order.dto.OrderResponse;
import com.hitesh.ecommerce.order.model.Order;
import com.hitesh.ecommerce.order.repository.OrderRepository;
import com.hitesh.ecommerce.order.service.OrderService;
import com.hitesh.ecommerce.order.service.discount.DiscountStrategy;
import com.hitesh.ecommerce.order.service.discount.DiscountStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        try {
            double productPrice = restTemplate.getForObject(
                "http://product-service/api/products/" + request.getProductId() + "/price",
                Double.class
            );

            DiscountStrategy strategy = DiscountStrategyFactory.getStrategy(request.getDiscountType());
            double finalPrice = strategy.applyDiscount(productPrice, request.getQuantity());

            Order order = Order.builder()
                    .userId(request.getUserId())
                    .productId(request.getProductId())
                    .quantity(request.getQuantity())
                    .totalPrice(finalPrice)
                    .orderDate(LocalDateTime.now())
                    .build();

            order = orderRepository.save(order);
            log.info("Order placed successfully: {}", order.getId());

            return OrderResponse.builder()
                    .orderId(order.getId())
                    .finalPrice(finalPrice)
                    .message("Order placed successfully.")
                    .build();

        } catch (Exception e) {
            log.error("Failed to place order: {}", e.getMessage());
            throw new RuntimeException("Failed to place order");
        }
    }
}

