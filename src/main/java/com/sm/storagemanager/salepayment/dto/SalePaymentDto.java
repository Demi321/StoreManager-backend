package com.sm.storagemanager.salepayment.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record SalePaymentDto(
        Long id,
        Long saleId,
        Long paymentMethodId,
        BigDecimal amount,
        String paymentReference,
        OffsetDateTime paidAt
) {
}
