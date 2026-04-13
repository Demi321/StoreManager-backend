package com.sm.storagemanager.sale.dto;

import com.sm.storagemanager.shared.enums.SaleStatus;
import com.sm.storagemanager.shared.enums.SaleType;
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
public class SaleDto {

    private Long id;
    private Long branchId;
    private Long warehouseId;
    private Long userId;
    private Long customerId;
    private String saleFolio;
    private OffsetDateTime saleDate;
    private BigDecimal subtotal;
    private BigDecimal discountTotal;
    private BigDecimal taxTotal;
    private BigDecimal total;
    private SaleStatus status;
    private SaleType saleType;
    private String notes;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
