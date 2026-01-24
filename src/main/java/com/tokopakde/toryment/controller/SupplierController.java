package com.tokopakde.toryment.controller;

import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.supplier.CreateSupplierReqDTO;
import com.tokopakde.toryment.dto.supplier.SupplierResDTO;
import com.tokopakde.toryment.dto.supplier.UpdateSupplierReqDTO;
import com.tokopakde.toryment.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity<Page<SupplierResDTO>> getSuppliers(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "name"));
        Page<SupplierResDTO> res = supplierService.getSuppliers(pageable);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SupplierResDTO> getSupplierById(@PathVariable String id) {
        var response = supplierService.getSupplierById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResDTO> createSupplier(@Valid @RequestBody CreateSupplierReqDTO request) {
        var response = supplierService.createSupplier(request);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResDTO> updateSupplier(@PathVariable String id,
                                                       @Valid @RequestBody UpdateSupplierReqDTO request) {
        var response = supplierService.updateSupplier(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResDTO> deleteSupplier(@PathVariable String id) {
        var response = supplierService.deleteSupplier(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
