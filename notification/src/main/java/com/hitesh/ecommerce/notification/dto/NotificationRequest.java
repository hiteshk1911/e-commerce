package com.hitesh.ecommerce.notification.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {
    private String recipient;
    private String message;
    private String type; // EMAIL, SMS, PUSH
}

