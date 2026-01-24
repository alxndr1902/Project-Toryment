package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.product.CreateProductReqDTO;
import com.tokopakde.toryment.dto.product.ProductResDTO;
import com.tokopakde.toryment.dto.product.UpdateProductReqDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResDTO> getProducts(Pageable pageable);

    ProductResDTO getProductById(String id);

    CreateResDTO  createProduct(CreateProductReqDTO request);

    UpdateResDTO updateProduct(String id, UpdateProductReqDTO request);

    CommonResDTO deleteProduct(String id);
}
