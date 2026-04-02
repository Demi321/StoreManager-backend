package com.sm.storagemanager.saleitem.dto;

import java.math.BigDecimal;

public record SaleItemDto(
        Long id,
        Long saleId,
        Long productId,
        BigDecimal quantity,
        BigDecimal unitPrice,
        BigDecimal unitDiscount,
        BigDecimal unitTax,
        BigDecimal lineSubtotal,
        BigDecimal lineTotal
) {
}
