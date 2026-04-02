package com.sm.storagemanager.appuser.dto;

import com.sm.storagemanager.shared.enums.UserStatus;
import java.time.OffsetDateTime;

public record AppUserDto(
        Long id,
        Long entityId,
        Long roleId,
        String firstName,
        String lastName,
        String middleName,
        String username,
        String email,
        String passwordHash,
        String phone,
        UserStatus status,
        OffsetDateTime lastLoginAt,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
