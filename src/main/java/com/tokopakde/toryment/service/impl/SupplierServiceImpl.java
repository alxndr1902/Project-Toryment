package com.tokopakde.toryment.service.impl;

import com.tokopakde.toryment.constant.Message;
import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.supplier.CreateSupplierReqDTO;
import com.tokopakde.toryment.dto.supplier.SupplierResDTO;
import com.tokopakde.toryment.dto.supplier.UpdateSupplierReqDTO;
import com.tokopakde.toryment.exceptiohandler.exception.DuplicateException;
import com.tokopakde.toryment.exceptiohandler.exception.NotFoundException;
import com.tokopakde.toryment.mapper.SupplierMapper;
import com.tokopakde.toryment.model.company.Supplier;
import com.tokopakde.toryment.mapper.PageMapper;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.repository.SupplierRepo;
import com.tokopakde.toryment.service.BaseService;
import com.tokopakde.toryment.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl extends BaseService implements SupplierService {
    private final SupplierRepo supplierRepository;
    private final SupplierMapper supplierMapper;
    private final PageMapper pageMapper;

    @Override
    public PageRes<SupplierResDTO> getSuppliers(Pageable pageable) {
        Page<Supplier> suppliers = supplierRepository.findAllBy(pageable);
        PageRes<SupplierResDTO> pages = pageMapper.toPageResponse(suppliers, supplierMapper::fromEntity);
        return pages;
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
        if (supplierRepository.existsByName(request.getName())) {
            throw new DuplicateException("Name Is Not Available");
        }

        if (supplierRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateException("Phone Number Is Not Available");
        }

        var supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setPhoneNumber(request.getPhoneNumber());
        var savedSupplier = supplierRepository.save(prepareCreate(supplier));
        return new  CreateResDTO(savedSupplier.getId(), Message.CREATED.getDescription());
    }

    @Override
    public UpdateResDTO updateSupplier(String id, UpdateSupplierReqDTO request) {
        var supplier = findSupplierById(id);
        if (!supplier.getVersion().equals(request.getVersion())) {
            throw new DuplicateException("Error Updating Supplier, Please Refresh The Page");
        }

        if (!supplier.getName().equals(request.getName())) {
            if (supplierRepository.existsByName(request.getName())) {
                throw new DuplicateException("Name Is Not Available");
            }
        }

        if (!supplier.getPhoneNumber().equals(request.getPhoneNumber())) {
            if (supplierRepository.existsByPhoneNumber(request.getPhoneNumber())) {
                throw new DuplicateException("Phone Number Is Not Available");
            }
        }
        supplier.setName(request.getName());
        supplier.setPhoneNumber(request.getPhoneNumber());

        var updatedSupplier = supplierRepository.saveAndFlush(prepareUpdate(supplier));
        return new UpdateResDTO(updatedSupplier.getVersion(),  Message.UPDATED.getDescription());
    }

    @Override
    public CommonResDTO deleteSupplier(String id) {
        var supplier =  findSupplierById(id);
        supplierRepository.delete(supplier);
        return new  CommonResDTO(Message.DELETED.getDescription());
    }

    private Supplier findSupplierById(String id) {
        var supplierId = parseUUID(id);
        var supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));
        return supplier;
    }
}
