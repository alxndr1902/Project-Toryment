package com.tokopakde.toryment.dto.supplier;

import com.tokopakde.toryment.model.company.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SupplierResDTO {
    private UUID id;
    private String name;
    private String phoneNumber;
    private Integer version;
    
    public static SupplierResDTO fromEntity(Supplier supplier) {
        return new SupplierResDTO(supplier.getId(), supplier.getName(),
                supplier.getPhoneNumber(), supplier.getVersion());
    }
}
