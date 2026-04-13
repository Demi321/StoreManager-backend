package com.sm.storagemanager.branchaddress.dto;

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
public class BranchAddressDto {

    private Long id;
    private Long branchId;
    private Long countryId;
    private Long stateId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postalCode;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
