package com.hitesh.ecommerce.inventory.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Integer quantity;

    private String warehouseLocation;
}

