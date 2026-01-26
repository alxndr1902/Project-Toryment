package com.tokopakde.toryment.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class  CreateProductReqDTO {
    @NotBlank(message = "Code Is Required")
    @Size(max = 20, message = "Code Maximum Length Is 20 Characters")
    private String code;

    @NotBlank(message = "Name Is Required")
    @Size(max = 100, message = "Name Maximum Length Is 20 Characters")
    private String name;

    @NotNull(message = "Stock Is Required")
    @Min(value = 1, message = "Minimum Stock Is 1")
    private String stock;

    @NotBlank(message = "Category Is Required")
    @Size(min = 36, max = 36)
    private String categoryId;
}
