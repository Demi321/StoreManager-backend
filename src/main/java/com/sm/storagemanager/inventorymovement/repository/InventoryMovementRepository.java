package com.sm.storagemanager.inventorymovement.repository;

import com.sm.storagemanager.inventorymovement.entity.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
}
