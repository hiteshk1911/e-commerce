package com.hitesh.ecommerce.notification.service.impl;

import com.hitesh.ecommerce.notification.dto.NotificationRequest;
import com.hitesh.ecommerce.notification.strategy.NotificationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("EMAIL")
public class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(NotificationRequest request) {
        log.info("Sending EMAIL to {}: {}", request.getRecipient(), request.getMessage());
        // Add SMTP integration here
    }
}

