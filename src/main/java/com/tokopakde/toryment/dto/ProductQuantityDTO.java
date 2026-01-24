package com.tokopakde.toryment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductQuantityDTO {
    @NotBlank(message = "Product Is Required")
    @Size(min = 36, max = 36)
    private String productId;

    @NotNull(message = "Quantity Is Required")
    @Min(value = 1, message = "Quantity Must Be At Least 1")
    private Integer quantity;
}
