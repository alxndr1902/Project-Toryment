package com.tokopakde.toryment.dto.history;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class HistoryResDTO {
    private final UUID id;
    private final Integer quantity;
    private final String statusCode;
    private final LocalDateTime dateTime;
}
