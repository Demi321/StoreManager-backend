package com.sm.storagemanager.productcategory.dto;

import com.sm.storagemanager.shared.enums.CategoryStatus;
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
public class ProductCategoryDto {

    private Long id;
    private Long branchId;
    private String name;
    private String description;
    private CategoryStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
