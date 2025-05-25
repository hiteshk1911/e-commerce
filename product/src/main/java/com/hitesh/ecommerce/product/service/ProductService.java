package com.hitesh.ecommerce.product.service;

import com.hitesh.ecommerce.product.dto.ProductRequest;
import com.hitesh.ecommerce.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long id);
}

