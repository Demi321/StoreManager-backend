package com.sm.storagemanager.paymentmethod.dto;

import com.sm.storagemanager.shared.enums.PaymentMethodCode;
import com.sm.storagemanager.shared.enums.PaymentMethodStatus;
import java.time.OffsetDateTime;

public record PaymentMethodDto(
        Long id,
        PaymentMethodCode code,
        String name,
        String description,
        PaymentMethodStatus status,
        OffsetDateTime createdAt
) {
}
