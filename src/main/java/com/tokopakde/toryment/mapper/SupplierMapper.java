package com.tokopakde.toryment.mapper;

import com.tokopakde.toryment.dto.supplier.SupplierResDTO;
import com.tokopakde.toryment.model.company.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierResDTO fromEntity(Supplier supplier);
}
