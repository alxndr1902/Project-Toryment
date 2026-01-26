package com.tokopakde.toryment.dto.resupply;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResupplyDetailResDTO {
    private final String productName;
    private final String productCode;
    private final Integer quantity;
}
