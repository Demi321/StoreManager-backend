package com.sm.storagemanager.rolepermission.dto;

import java.time.OffsetDateTime;

public record RolePermissionDto(
        Long roleId,
        Long permissionId,
        OffsetDateTime createdAt
) {
}
