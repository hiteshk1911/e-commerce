package com.hitesh.ecommerce.cart.controller;

import com.hitesh.ecommerce.cart.dto.CartItemDTO;
import com.hitesh.ecommerce.cart.dto.CartResponseDTO;
import com.hitesh.ecommerce.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<CartResponseDTO> addToCart(@PathVariable Long userId, @RequestBody CartItemDTO itemDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addToCart(userId, itemDTO));
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<CartResponseDTO> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeFromCart(userId, productId));
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully.");
    }
}

