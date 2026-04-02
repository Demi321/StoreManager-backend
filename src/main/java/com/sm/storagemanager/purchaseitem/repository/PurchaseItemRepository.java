package com.sm.storagemanager.purchaseitem.repository;

import com.sm.storagemanager.purchaseitem.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
}
