package com.tokopakde.toryment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResDTO<T> {
    private final T message;
}
