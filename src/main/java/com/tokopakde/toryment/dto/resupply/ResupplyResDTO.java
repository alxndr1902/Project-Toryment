package com.tokopakde.toryment.dto.resupply;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ResupplyResDTO {
    private final UUID id;
    private final LocalDateTime dateTime;
    private final String code;
    private final String supplierName;
    private final Integer version;
}
