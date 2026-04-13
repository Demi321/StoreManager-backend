package com.sm.storagemanager.warehouse.dto;

import com.sm.storagemanager.shared.enums.WarehouseType;
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
public class WarehouseDto {

    private Long id;
    private Long branchId;
    private String name;
    private WarehouseType type;
    private String phone;
    private boolean active;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
