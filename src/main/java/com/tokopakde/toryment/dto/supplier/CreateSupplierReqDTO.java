package com.tokopakde.toryment.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateSupplierReqDTO {
    @NotBlank(message = "Supplier Name Is Required")
    @Size(max = 100, message = "Supplier Name Maximum Length Is 100 Characters")
    private String name;

    @NotBlank(message = "Supplier Phone Number Is Required")
    @Size(max = 100, message = "Supplier Phone Number Maximum Length Is 100 Characters")
    private String phoneNumber;
}
