package com.hitesh.ecommerce.inventory.service;

import com.hitesh.ecommerce.inventory.dto.*;
import com.hitesh.ecommerce.inventory.model.Inventory;
import com.hitesh.ecommerce.inventory.repository.InventoryRepository;
import com.hitesh.ecommerce.inventory.exception.InventoryNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public InventoryResponse createInventory(InventoryRequest request) {
        Inventory inventory = Inventory.builder()
            .productId(request.getProductId())
            .quantity(request.getQuantity())
            .warehouseLocation(request.getWarehouseLocation())
            .build();

        Inventory saved = inventoryRepository.save(inventory);
        log.info("Inventory created: {}", saved.getId());

        return mapToResponse(saved);
    }

    @Override
    public InventoryResponse updateInventory(Long id, InventoryRequest request) {
        Inventory inventory = inventoryRepository.findById(id)
            .orElseThrow(() -> new InventoryNotFoundException("Inventory not found for id: " + id));

        inventory.setQuantity(request.getQuantity());
        inventory.setWarehouseLocation(request.getWarehouseLocation());

        Inventory updated = inventoryRepository.save(inventory);
        log.info("Inventory updated: {}", updated.getId());

        return mapToResponse(updated);
    }

    @Override
    public InventoryResponse getInventoryByProductId(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
            .orElseThrow(() -> new InventoryNotFoundException("Inventory not found for productId: " + productId));
        return mapToResponse(inventory);
    }

    @Override
    public List<InventoryResponse> getAllInventories() {
        return inventoryRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public void deleteInventory(Long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new InventoryNotFoundException("Inventory not found for id: " + id);
        }
        inventoryRepository.deleteById(id);
        log.info("Inventory deleted: {}", id);
    }

    private InventoryResponse mapToResponse(Inventory inventory) {
        return InventoryResponse.builder()
            .id(inventory.getId())
            .productId(inventory.getProductId())
            .quantity(inventory.getQuantity())
            .warehouseLocation(inventory.getWarehouseLocation())
            .build();
    }
}

