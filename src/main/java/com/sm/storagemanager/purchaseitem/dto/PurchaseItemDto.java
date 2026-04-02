package com.sm.storagemanager.purchaseitem.dto;

import java.math.BigDecimal;

public record PurchaseItemDto(
        Long id,
        Long purchaseId,
        Long productId,
        BigDecimal quantity,
        BigDecimal unitCost,
        BigDecimal lineSubtotal,
        BigDecimal lineTotal
) {
}
