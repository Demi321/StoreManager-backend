package com.sm.storagemanager.inventory.repository;

import com.sm.storagemanager.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
