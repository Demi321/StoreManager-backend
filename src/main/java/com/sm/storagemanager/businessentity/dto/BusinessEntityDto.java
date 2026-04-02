package com.sm.storagemanager.businessentity.dto;

import com.sm.storagemanager.shared.enums.EntityStatus;
import java.time.OffsetDateTime;

public record BusinessEntityDto(
        Long id,
        String name,
        String legalName,
        String taxId,
        String phone,
        String email,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String country,
        String postalCode,
        EntityStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
