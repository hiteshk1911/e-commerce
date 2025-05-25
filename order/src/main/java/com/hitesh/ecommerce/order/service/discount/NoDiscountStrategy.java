package com.hitesh.ecommerce.order.service.discount;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double basePrice, int quantity) {
        return basePrice * quantity;
    }
}

