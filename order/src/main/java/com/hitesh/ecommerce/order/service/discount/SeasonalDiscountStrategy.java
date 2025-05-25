package com.hitesh.ecommerce.order.service.discount;

public class SeasonalDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double basePrice, int quantity) {
        return basePrice * quantity * 0.9; // 10% off
    }
}

