package com.hitesh.ecommerce.inventory.service;

import com.hitesh.ecommerce.inventory.dto.*;

import java.util.List;

public interface InventoryService {
    InventoryResponse createInventory(InventoryRequest request);
    InventoryResponse updateInventory(Long id, InventoryRequest request);
    InventoryResponse getInventoryByProductId(Long productId);
    List<InventoryResponse> getAllInventories();
    void deleteInventory(Long id);
}

