package com.hitesh.ecommerce.inventory.controller;

import com.hitesh.ecommerce.inventory.dto.*;
import com.hitesh.ecommerce.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> create(@RequestBody InventoryRequest request) {
        try {
            return ResponseEntity.status(201).body(inventoryService.createInventory(request));
        } catch (Exception e) {
            log.error("Error creating inventory", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> update(@PathVariable Long id, @RequestBody InventoryRequest request) {
        try {
            return ResponseEntity.ok(inventoryService.updateInventory(id, request));
        } catch (Exception e) {
            log.error("Error updating inventory", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getByProduct(@PathVariable Long productId) {
        try {
            return ResponseEntity.ok(inventoryService.getInventoryByProductId(productId));
        } catch (Exception e) {
            log.error("Error fetching inventory by product ID", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAll() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            inventoryService.deleteInventory(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting inventory", e);
            return ResponseEntity.notFound().build();
        }
    }
}

