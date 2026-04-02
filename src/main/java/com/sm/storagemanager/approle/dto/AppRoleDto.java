package com.sm.storagemanager.approle.dto;

import com.sm.storagemanager.shared.enums.RoleStatus;
import java.time.OffsetDateTime;

public record AppRoleDto(
        Long id,
        Long entityId,
        String name,
        String description,
        RoleStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
