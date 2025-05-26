package com.hitesh.ecommerce.notification.strategy;

import com.hitesh.ecommerce.notification.dto.NotificationRequest;

public interface NotificationStrategy {
    void sendNotification(NotificationRequest request);
}

