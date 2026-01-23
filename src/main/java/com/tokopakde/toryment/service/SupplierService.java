package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.supplier.CreateSupplierReqDTO;
import com.tokopakde.toryment.dto.supplier.SupplierResDTO;
import com.tokopakde.toryment.dto.supplier.UpdateSupplierReqDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
    Page<SupplierResDTO> getSuppliers(Pageable pageable);

    SupplierResDTO getSupplierById(String id);

    CreateResDTO createSupplier(CreateSupplierReqDTO request);

    UpdateResDTO updateSupplier(String id, UpdateSupplierReqDTO request);

    CommonResDTO deleteSupplier(String id);
}
