package com.tokopakde.toryment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateTransactionResDTO {
    private final UUID id;
    private final String code;
    private String message;
}
