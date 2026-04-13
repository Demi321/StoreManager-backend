package com.sm.storagemanager.purchaseitem.dto;

import java.math.BigDecimal;
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
public class PurchaseItemDto {

    private Long id;
    private Long purchaseId;
    private Long productId;
    private BigDecimal quantity;
    private BigDecimal unitCost;
    private BigDecimal lineSubtotal;
    private BigDecimal lineTotal;
}
