package com.sm.storagemanager.supplier.dto;

import com.sm.storagemanager.shared.enums.SupplierStatus;
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
public class SupplierDto {

    private Long id;
    private Long branchId;
    private String name;
    private String taxId;
    private String phone;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private SupplierStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
