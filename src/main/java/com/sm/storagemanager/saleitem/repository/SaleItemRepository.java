package com.sm.storagemanager.saleitem.repository;

import com.sm.storagemanager.saleitem.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
