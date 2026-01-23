package com.tokopakde.toryment.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckoutDetailResDTO {
    private final String productCode;
    private final Integer quantity;
}
