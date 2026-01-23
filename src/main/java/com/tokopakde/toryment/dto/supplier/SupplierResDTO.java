package com.tokopakde.toryment.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SupplierResDTO {
    private final UUID id;
    private final String name;
    private final String phoneNumber;
    private final Integer version;
}
