package com.tokopakde.toryment.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SupplierResDTO {
    private UUID id;
    private String name;
    private String phoneNumber;
    private Integer version;
}
