package com.sm.storagemanager.permission.dto;

import com.sm.storagemanager.shared.enums.PermissionStatus;
import java.time.OffsetDateTime;

public record PermissionDto(
        Long id,
        String code,
        String name,
        String description,
        String module,
        PermissionStatus status,
        OffsetDateTime createdAt
) {
}
