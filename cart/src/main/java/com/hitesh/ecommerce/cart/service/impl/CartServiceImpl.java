package com.hitesh.ecommerce.cart.service.impl;

import com.hitesh.ecommerce.cart.dto.CartItemDTO;
import com.hitesh.ecommerce.cart.dto.CartResponseDTO;
import com.hitesh.ecommerce.cart.entity.CartItem;
import com.hitesh.ecommerce.cart.exception.CartOperationException;
import com.hitesh.ecommerce.cart.repository.CartItemRepository;
import com.hitesh.ecommerce.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartRepo;

    @Override
    public CartResponseDTO getCart(Long userId) {
        try {
            List<CartItemDTO> items = cartRepo.findByUserId(userId).stream()
                .map(item -> new CartItemDTO(item.getProductId(), item.getQuantity()))
                .collect(Collectors.toList());

            return new CartResponseDTO(userId, items);
        } catch (Exception e) {
            log.error("Failed to fetch cart for user {}", userId, e);
            throw new CartOperationException("Could not retrieve cart");
        }
    }

    @Override
    public CartResponseDTO addToCart(Long userId, CartItemDTO itemDTO) {
        try {
            CartItem item = CartItem.builder()
                    .userId(userId)
                    .productId(itemDTO.getProductId())
                    .quantity(itemDTO.getQuantity())
                    .build();
            cartRepo.save(item);
            return getCart(userId);
        } catch (Exception e) {
            log.error("Failed to add item to cart for user {}", userId, e);
            throw new CartOperationException("Could not add item to cart");
        }
    }

    @Override
    public CartResponseDTO removeFromCart(Long userId, Long productId) {
        try {
            cartRepo.deleteByUserIdAndProductId(userId, productId);
            return getCart(userId);
        } catch (Exception e) {
            log.error("Failed to remove item from cart for user {}", userId, e);
            throw new CartOperationException("Could not remove item from cart");
        }
    }

    @Override
    public void clearCart(Long userId) {
        try {
            cartRepo.deleteAll(cartRepo.findByUserId(userId));
        } catch (Exception e) {
            log.error("Failed to clear cart for user {}", userId, e);
            throw new CartOperationException("Could not clear cart");
        }
    }
}

