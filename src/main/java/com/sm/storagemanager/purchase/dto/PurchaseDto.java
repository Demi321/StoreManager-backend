package com.sm.storagemanager.purchase.dto;

import com.sm.storagemanager.shared.enums.PurchaseStatus;
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
public class PurchaseDto {

    private Long id;
    private Long branchId;
    private Long supplierId;
    private Long warehouseId;
    private Long userId;
    private String purchaseFolio;
    private OffsetDateTime purchaseDate;
    private BigDecimal subtotal;
    private BigDecimal taxTotal;
    private BigDecimal total;
    private PurchaseStatus status;
    private String notes;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
