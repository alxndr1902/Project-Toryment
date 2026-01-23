package com.tokopakde.toryment.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CheckoutResDTO {
    private final UUID id;
    private final LocalDateTime dateTime;
    private final String branchCode;
    private final Integer version;
}
