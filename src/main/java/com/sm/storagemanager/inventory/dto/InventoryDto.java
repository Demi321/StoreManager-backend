package com.sm.storagemanager.inventory.dto;

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
public class InventoryDto {

    private Long id;
    private Long branchId;
    private Long warehouseId;
    private Long productId;
    private BigDecimal stockOnHand;
    private BigDecimal stockReserved;
    private BigDecimal stockAvailable;
    private BigDecimal averageCost;
    private OffsetDateTime updatedAt;
}
