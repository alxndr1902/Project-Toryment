package com.tokopakde.toryment.dto.branch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBranchReqDTO {
    @NotBlank(message = "Branch Code Is Required")
    @Size(max = 20, message = "Branch Code Maximum Length Is 20 Characters")
    private String code;

    @NotBlank(message = "Branch Name Is Required")
    @Size(max = 100, message = "Branch Name Maximum Length Is 100 Characters")
    private String name;

    @NotBlank(message = "Branch Address Is Required")
    @Size(max = 300, message = "Branch Address Maximum Length Is 300 Characters")
    private String address;

    @NotBlank(message = "Branch Phone Number Is Required")
    @Size(max = 20, message = "Branch Phone Number Maximum Length Is 20 Characters")
    private String phoneNumber;
}
