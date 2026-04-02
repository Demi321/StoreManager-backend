package com.sm.storagemanager.warehouse.dto;

import com.sm.storagemanager.shared.enums.WarehouseType;
import java.time.OffsetDateTime;

public record WarehouseDto(
        Long id,
        Long entityId,
        String name,
        WarehouseType type,
        String phone,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String country,
        String postalCode,
        boolean active,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
