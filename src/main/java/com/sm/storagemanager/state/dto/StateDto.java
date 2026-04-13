package com.sm.storagemanager.state.dto;

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
public class StateDto {

    private Long id;
    private Long countryId;
    private String code;
    private String name;
    private OffsetDateTime createdAt;
}
