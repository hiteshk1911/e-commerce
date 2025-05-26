package com.hitesh.ecommerce.payment.controller;

import com.hitesh.ecommerce.payment.dto.PaymentRequest;
import com.hitesh.ecommerce.payment.dto.PaymentResponse;
import com.hitesh.ecommerce.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> pay(@RequestBody PaymentRequest request) {
        try {
            PaymentResponse response = paymentService.processPayment(request);
            return switch (response.getStatus()) {
                case "SUCCESS" -> ResponseEntity.ok(response);
                case "PENDING" -> ResponseEntity.accepted().body(response);
                default -> ResponseEntity.badRequest().body(response);
            };
        } catch (Exception e) {
            log.error("Exception in processing payment", e);
            return ResponseEntity.internalServerError()
                    .body(PaymentResponse.builder()
                            .status("FAILURE")
                            .message("Internal server error")
                            .build());
        }
    }
}

