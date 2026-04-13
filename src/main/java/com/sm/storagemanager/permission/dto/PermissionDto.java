package com.sm.storagemanager.permission.dto;

import com.sm.storagemanager.shared.enums.PermissionStatus;
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
public class PermissionDto {

    private Long id;
    private String code;
    private String name;
    private String description;
    private String module;
    private PermissionStatus status;
    private OffsetDateTime createdAt;
}
