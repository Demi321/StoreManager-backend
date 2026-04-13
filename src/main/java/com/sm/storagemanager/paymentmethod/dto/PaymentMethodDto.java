package com.sm.storagemanager.paymentmethod.dto;

import com.sm.storagemanager.shared.enums.PaymentMethodCode;
import com.sm.storagemanager.shared.enums.PaymentMethodStatus;
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
public class PaymentMethodDto {

    private Long id;
    private PaymentMethodCode code;
    private String name;
    private String description;
    private PaymentMethodStatus status;
    private OffsetDateTime createdAt;
}
