package com.sm.storagemanager.inventory.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record InventoryDto(
        Long id,
        Long entityId,
        Long warehouseId,
        Long productId,
        BigDecimal stockOnHand,
        BigDecimal stockReserved,
        BigDecimal stockAvailable,
        BigDecimal averageCost,
        OffsetDateTime updatedAt
) {
}
