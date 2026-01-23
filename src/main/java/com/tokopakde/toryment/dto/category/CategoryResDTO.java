package com.tokopakde.toryment.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CategoryResDTO {
    private UUID id;
    private String code;
    private String name;
    private Integer version;
}
