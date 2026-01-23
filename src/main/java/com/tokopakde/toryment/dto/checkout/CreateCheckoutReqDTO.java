package com.tokopakde.toryment.dto.checkout;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateCheckoutReqDTO {
    @NotBlank(message = "Branch Is Required")
    @Size(min = 36, max = 36)
    private String branchId;

    @NotEmpty(message = "Products Are Required")
    private List<String> productIds;
}
