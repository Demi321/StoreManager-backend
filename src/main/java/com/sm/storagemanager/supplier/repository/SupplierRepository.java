package com.sm.storagemanager.supplier.repository;

import com.sm.storagemanager.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
