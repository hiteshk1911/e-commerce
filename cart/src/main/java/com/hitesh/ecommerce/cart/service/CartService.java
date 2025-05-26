package com.hitesh.ecommerce.cart.service;

import com.hitesh.ecommerce.cart.dto.CartItemDTO;
import com.hitesh.ecommerce.cart.dto.CartResponseDTO;

public interface CartService {
    CartResponseDTO getCart(Long userId);
    CartResponseDTO addToCart(Long userId, CartItemDTO item);
    CartResponseDTO removeFromCart(Long userId, Long productId);
    void clearCart(Long userId);
}

