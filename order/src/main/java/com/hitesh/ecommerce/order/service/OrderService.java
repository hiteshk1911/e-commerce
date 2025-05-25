package com.hitesh.ecommerce.order.service;

import com.hitesh.ecommerce.order.dto.OrderRequest;
import com.hitesh.ecommerce.order.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request);
}

