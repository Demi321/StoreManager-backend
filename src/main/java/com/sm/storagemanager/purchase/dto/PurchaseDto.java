package com.sm.storagemanager.purchase.dto;

import com.sm.storagemanager.shared.enums.PurchaseStatus;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PurchaseDto(
        Long id,
        Long entityId,
        Long supplierId,
        Long warehouseId,
        Long userId,
        String purchaseFolio,
        OffsetDateTime purchaseDate,
        BigDecimal subtotal,
        BigDecimal taxTotal,
        BigDecimal total,
        PurchaseStatus status,
        String notes,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
