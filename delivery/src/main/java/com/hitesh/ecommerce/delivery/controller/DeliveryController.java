package com.hitesh.ecommerce.delivery.controller;

import com.hitesh.ecommerce.delivery.dto.*;
import com.hitesh.ecommerce.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
@Slf4j
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<DeliveryResponse> createDelivery(@RequestBody DeliveryRequest request) {
        try {
            log.info("Received delivery creation request: {}", request);
            DeliveryResponse response = deliveryService.createDelivery(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error while creating delivery: {}", e.getMessage());
            throw e;
        }
    }
}


