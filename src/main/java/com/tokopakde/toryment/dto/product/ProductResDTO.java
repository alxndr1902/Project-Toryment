package com.tokopakde.toryment.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResDTO {
    private final String code;
    private final String name;
    private final Integer stock;
}
