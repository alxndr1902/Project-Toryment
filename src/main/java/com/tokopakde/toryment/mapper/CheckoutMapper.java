package com.tokopakde.toryment.mapper;

import com.tokopakde.toryment.dto.checkout.CheckoutDetailResDTO;
import com.tokopakde.toryment.dto.checkout.CheckoutResDTO;
import com.tokopakde.toryment.model.transaction.Checkout;
import com.tokopakde.toryment.model.transaction.CheckoutDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CheckoutMapper {
    CheckoutResDTO mapToDto(Checkout checkout);

    @Mapping(target = "productCode", source = "product.code")
    @Mapping(target = "productName", source = "product.name")
    CheckoutDetailResDTO mapDetailToDto(CheckoutDetail checkoutDetail);
}
