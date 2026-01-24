package com.tokopakde.toryment.dto.resupply;

import com.tokopakde.toryment.dto.ProductQuantityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UpdateResupplyReqDTO {
    @NotBlank(message = "Supplier Is Required")
    @Size(min = 36, max = 36)
    private String supplierId;

    @NotEmpty(message = "Products Are Required")
    private List<ProductQuantityDTO> products;

    @NotNull
    private Integer version;
}
