package com.sm.storagemanager.approle.dto;

import com.sm.storagemanager.shared.enums.RoleStatus;
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
public class AppRoleDto {

    private Long id;
    private Long branchId;
    private String name;
    private String description;
    private RoleStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
