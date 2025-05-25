package com.hitesh.ecommerce.product.service.impl;

import com.hitesh.ecommerce.product.dto.ProductRequest;
import com.hitesh.ecommerce.product.dto.ProductResponse;
import com.hitesh.ecommerce.product.exception.ProductNotFoundException;
import com.hitesh.ecommerce.product.model.Product;
import com.hitesh.ecommerce.product.repository.ProductRepository;
import com.hitesh.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        try {
            Product product = Product.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(request.getPrice())
                    .quantity(request.getQuantity())
                    .build();

            Product saved = productRepository.save(product);
            log.info("Product created with ID: {}", saved.getId());
            return mapToResponse(saved);
        } catch (Exception e) {
            log.error("Error creating product", e);
            throw e;
        }
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Product not found: {}", id);
                    return new ProductNotFoundException("Product not found with ID: " + id);
                });

        return mapToResponse(product);
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}

