package com.sm.storagemanager.rolepermission.dto;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermissionDto {

    private Long roleId;
    private Long permissionId;
    private OffsetDateTime createdAt;
}
