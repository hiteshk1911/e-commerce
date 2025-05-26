package com.hitesh.ecommerce.cart.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDTO {
    private Long userId;
    private List<CartItemDTO> items;
}

