package com.sm.storagemanager.warehouse.repository;

import com.sm.storagemanager.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
