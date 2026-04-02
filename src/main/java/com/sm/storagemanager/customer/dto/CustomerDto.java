package com.sm.storagemanager.customer.dto;

import com.sm.storagemanager.shared.enums.CustomerType;
import java.time.OffsetDateTime;

public record CustomerDto(
        Long id,
        Long entityId,
        String name,
        CustomerType customerType,
        String taxId,
        String phone,
        String email,
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
