package com.hitesh.ecommerce.payment.strategy;

import com.hitesh.ecommerce.payment.enums.PaymentMode;
import com.hitesh.ecommerce.payment.strategy.impl.CashOnDeliveryStrategy;
import com.hitesh.ecommerce.payment.strategy.impl.CreditCardPaymentStrategy;
import com.hitesh.ecommerce.payment.strategy.impl.UpiPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyFactory {

    private final CreditCardPaymentStrategy creditCardPaymentStrategy;
    private final UpiPaymentStrategy upiPaymentStrategy;
    private final CashOnDeliveryStrategy cashOnDeliveryStrategy;

    public PaymentStrategy getStrategy(PaymentMode mode) {
        return switch (mode) {
            case CREDIT_CARD -> creditCardPaymentStrategy;
            case UPI -> upiPaymentStrategy;
            case CASH_ON_DELIVERY -> cashOnDeliveryStrategy;
        };
    }
}

