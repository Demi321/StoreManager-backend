package com.sm.storagemanager.product.dto;

import com.sm.storagemanager.shared.enums.ProductStatus;
import java.math.BigDecimal;
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
public class ProductDto {

    private Long id;
    private Long branchId;
    private Long categoryId;
    private String sku;
    private String barcode;
    private String name;
    private String description;
    private String unitOfMeasure;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private BigDecimal minStock;
    private BigDecimal maxStock;
    private boolean tracksInventory;
    private ProductStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
