package com.tokopakde.toryment.mapper;

import com.tokopakde.toryment.dto.checkout.CheckoutResDTO;
import com.tokopakde.toryment.model.transaction.Checkout;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CheckoutMapper {
    @Mapping(target = "branchCode", source = "branch.code")
    CheckoutResDTO mapToDto(Checkout checkout);
}
