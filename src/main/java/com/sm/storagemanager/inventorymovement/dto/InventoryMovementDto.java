package com.sm.storagemanager.inventorymovement.dto;

import com.sm.storagemanager.shared.enums.InventoryMovementType;
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
public class InventoryMovementDto {

    private Long id;
    private Long branchId;
    private Long warehouseId;
    private Long productId;
    private Long userId;
    private InventoryMovementType movementType;
    private BigDecimal quantity;
    private BigDecimal stockBefore;
    private BigDecimal stockAfter;
    private BigDecimal unitCost;
    private String referenceType;
    private Long referenceId;
    private String notes;
    private OffsetDateTime createdAt;
}
