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
    private final String code;
    private final String branchName;
    private final Integer version;
}
