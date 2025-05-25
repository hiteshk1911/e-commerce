package com.hitesh.ecommerce.order.service.discount;

public class DiscountStrategyFactory {
    public static DiscountStrategy getStrategy(String type) {
        return switch (type.toUpperCase()) {
            case "SEASONAL" -> new SeasonalDiscountStrategy();
            case "NONE", default -> new NoDiscountStrategy();
        };
    }
}

