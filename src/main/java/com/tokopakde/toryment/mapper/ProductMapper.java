package com.tokopakde.toryment.mapper;

import com.tokopakde.toryment.dto.product.CreateProductReqDTO;
import com.tokopakde.toryment.dto.product.ProductResDTO;
import com.tokopakde.toryment.dto.product.UpdateProductReqDTO;
import com.tokopakde.toryment.model.company.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResDTO mapToDto(Product product);

    Product mapToEntity(CreateProductReqDTO request);
}
