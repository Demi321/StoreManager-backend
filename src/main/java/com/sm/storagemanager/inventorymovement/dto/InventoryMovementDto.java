package com.sm.storagemanager.inventorymovement.dto;

import com.sm.storagemanager.shared.enums.InventoryMovementType;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record InventoryMovementDto(
        Long id,
        Long entityId,
        Long warehouseId,
        Long productId,
        Long userId,
        InventoryMovementType movementType,
        BigDecimal quantity,
        BigDecimal stockBefore,
        BigDecimal stockAfter,
        BigDecimal unitCost,
        String referenceType,
        Long referenceId,
        String notes,
        OffsetDateTime createdAt
) {
}
