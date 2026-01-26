package com.tokopakde.toryment.dto.checkout;

import com.tokopakde.toryment.dto.ProductQuantityDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UpdateCheckoutReqDTO {
    @NotBlank(message = "Branch Is Required")
    @Size(min = 36, max = 36)
    private String branchId;

    @NotEmpty(message = "Products Are Required")
    @Valid
    private List<ProductQuantityDTO> products;

    @NotNull
    private Integer version;
}
