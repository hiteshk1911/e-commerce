package com.hitesh.ecommerce.order.service.discount;

public interface DiscountStrategy {
    double applyDiscount(double basePrice, int quantity);
}

