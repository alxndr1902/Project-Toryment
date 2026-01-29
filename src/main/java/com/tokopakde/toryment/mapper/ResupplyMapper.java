package com.tokopakde.toryment.mapper;

import com.tokopakde.toryment.dto.resupply.ResupplyDetailResDTO;
import com.tokopakde.toryment.dto.resupply.ResupplyResDTO;
import com.tokopakde.toryment.model.transaction.Resupply;
import com.tokopakde.toryment.model.transaction.ResupplyDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResupplyMapper {
    @Mapping(target = "supplierName", source = "supplier.name")
    ResupplyResDTO mapToDto(Resupply resupply);

    @Mapping(target = "productCode", source = "product.code")
    @Mapping(target = "productName", source = "product.name")
    ResupplyDetailResDTO mapDetailToDto(ResupplyDetail resupplyDetail);
}
