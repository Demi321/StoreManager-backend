package com.sm.storagemanager.sale.dto;

import com.sm.storagemanager.shared.enums.SaleStatus;
import com.sm.storagemanager.shared.enums.SaleType;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record SaleDto(
        Long id,
        Long entityId,
        Long warehouseId,
        Long userId,
        Long customerId,
        String saleFolio,
        OffsetDateTime saleDate,
        BigDecimal subtotal,
        BigDecimal discountTotal,
        BigDecimal taxTotal,
        BigDecimal total,
        SaleStatus status,
        SaleType saleType,
        String notes,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
