package com.hitesh.ecommerce.notification.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitesh.ecommerce.notification.dto.NotificationRequest;
import com.hitesh.ecommerce.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "notification-events", groupId = "notification-group")
    public void listen(String message) {
        try {
            NotificationRequest request = objectMapper.readValue(message, NotificationRequest.class);
            notificationService.notify(request);
        } catch (Exception e) {
            log.error("Failed to process Kafka notification message", e);
        }
    }
}

