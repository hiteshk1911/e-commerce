package com.hitesh.ecommerce.notification.controller;

import com.hitesh.ecommerce.notification.dto.NotificationRequest;
import com.hitesh.ecommerce.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        try {
            notificationService.notify(request);
            return ResponseEntity.ok("Notification sent successfully");
        } catch (Exception e) {
            log.error("Error in NotificationController", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send notification");
        }
    }
}

