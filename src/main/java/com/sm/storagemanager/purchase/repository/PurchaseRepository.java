package com.sm.storagemanager.purchase.repository;

import com.sm.storagemanager.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
