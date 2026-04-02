package com.sm.storagemanager.product.dto;

import com.sm.storagemanager.shared.enums.ProductStatus;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ProductDto(
        Long id,
        Long entityId,
        Long categoryId,
        String sku,
        String barcode,
        String name,
        String description,
        String unitOfMeasure,
        BigDecimal costPrice,
        BigDecimal salePrice,
        BigDecimal minStock,
        BigDecimal maxStock,
        boolean tracksInventory,
        ProductStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
