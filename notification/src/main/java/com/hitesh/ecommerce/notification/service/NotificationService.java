package com.hitesh.ecommerce.notification.service;

import com.hitesh.ecommerce.notification.dto.NotificationRequest;

public interface NotificationService {
    void notify(NotificationRequest request);
}

