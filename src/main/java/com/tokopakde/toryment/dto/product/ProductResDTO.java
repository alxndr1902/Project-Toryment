package com.tokopakde.toryment.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProductResDTO {
    private final UUID id;
    private final String code;
    private final String name;
    private final Integer stock;
    private final Integer version;
}
