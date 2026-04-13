package com.sm.storagemanager.salepayment.dto;

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
public class SalePaymentDto {

    private Long id;
    private Long saleId;
    private Long paymentMethodId;
    private BigDecimal amount;
    private String paymentReference;
    private OffsetDateTime paidAt;
}
