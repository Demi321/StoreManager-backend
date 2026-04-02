package com.sm.storagemanager.supplier.dto;

import com.sm.storagemanager.shared.enums.SupplierStatus;
import java.time.OffsetDateTime;

public record SupplierDto(
        Long id,
        Long entityId,
        String name,
        String taxId,
        String phone,
        String email,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String country,
        String postalCode,
        SupplierStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
