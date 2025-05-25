package com.hitesh.ecommerce.inventory.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InventoryRequest {
    private Long productId;
    private Integer quantity;
    private String warehouseLocation;
}

