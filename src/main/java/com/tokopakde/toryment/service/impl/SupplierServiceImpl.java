package com.tokopakde.toryment.service.impl;

import com.tokopakde.toryment.constant.Message;
import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.supplier.CreateSupplierReqDTO;
import com.tokopakde.toryment.dto.supplier.SupplierResDTO;
import com.tokopakde.toryment.dto.supplier.UpdateSupplierReqDTO;
import com.tokopakde.toryment.exceptiohandler.exception.NotFoundException;
import com.tokopakde.toryment.model.company.Supplier;
import com.tokopakde.toryment.repository.SupplierRepository;
import com.tokopakde.toryment.service.BaseService;
import com.tokopakde.toryment.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl extends BaseService implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Override
    public Page<SupplierResDTO> getSuppliers(Pageable pageable) {
        Page<SupplierResDTO> dtos = supplierRepository.findAll(pageable).map(this::mapToDto);
        return dtos;
    }

    @Override
    public SupplierResDTO getSupplierById(String id) {
        var supplier = findSupplierById(id);
        var dto = mapToDto(supplier);
        return dto;
    }

    private SupplierResDTO mapToDto(Supplier supplier) {
        var dto = new SupplierResDTO(supplier.getId(), supplier.getName(),
                supplier.getPhoneNumber(), supplier.getVersion());
        return dto;
    }

    @Override
    public CreateResDTO createSupplier(CreateSupplierReqDTO request) {
        var supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setPhoneNumber(request.getPhoneNumber());
        var savedSupplier = supplierRepository.save(prepareCreate(supplier));
        return new  CreateResDTO(savedSupplier.getId(), Message.CREATED.getName());
    }

    @Override
    public UpdateResDTO updateSupplier(String id, UpdateSupplierReqDTO request) {
        var supplier = findSupplierById(id);
        supplier.setName(request.getName());
        supplier.setPhoneNumber(request.getPhoneNumber());

        var updatedSupplier = supplierRepository.saveAndFlush(prepareUpdate(supplier));
        return new UpdateResDTO(updatedSupplier.getVersion(),  Message.UPDATED.getName());
    }

    @Override
    public CommonResDTO deleteSupplier(String id) {
        var supplier =  findSupplierById(id);
        supplierRepository.delete(supplier);
        return new  CommonResDTO(Message.DELETED.getName());
    }

    private Supplier findSupplierById(String id) {
        var supplierId = convertToUUID(id);
        var supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));
        return supplier;
    }
}
