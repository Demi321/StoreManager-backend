package com.sm.storagemanager.businessentity.dto;

import com.sm.storagemanager.shared.enums.EntityStatus;
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
public class BusinessEntityDto {

    private Long id;
    private Long sectorId;
    private String name;
    private String legalName;
    private String taxId;
    private String phone;
    private String email;
    private EntityStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
