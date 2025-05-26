package com.hitesh.ecommerce.notification.service.impl;

import com.hitesh.ecommerce.notification.dto.NotificationRequest;
import com.hitesh.ecommerce.notification.exception.NotificationException;
import com.hitesh.ecommerce.notification.service.NotificationService;
import com.hitesh.ecommerce.notification.strategy.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final ApplicationContext context;

    @Override
    public void notify(NotificationRequest request) {
        try {
            NotificationStrategy strategy = (NotificationStrategy) context.getBean(request.getType());
            strategy.sendNotification(request);
        } catch (Exception e) {
            log.error("Failed to send notification", e);
            throw new NotificationException("Notification failed: " + e.getMessage());
        }
    }
}

