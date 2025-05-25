package com.hitesh.ecommerce.order.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Long userId;
    private Long productId;
    private int quantity;
    private String discountType; // e.g., NONE, SEASONAL
}

