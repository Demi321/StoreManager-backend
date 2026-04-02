package com.sm.storagemanager.productcategory.dto;

import com.sm.storagemanager.shared.enums.CategoryStatus;
import java.time.OffsetDateTime;

public record ProductCategoryDto(
        Long id,
        Long entityId,
        String name,
        String description,
        CategoryStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
