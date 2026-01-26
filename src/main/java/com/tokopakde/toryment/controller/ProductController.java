package com.tokopakde.toryment.controller;

import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.branch.BranchResDTO;
import com.tokopakde.toryment.dto.branch.CreateBranchReqDTO;
import com.tokopakde.toryment.dto.branch.UpdateBranchReqDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.dto.product.CreateProductReqDTO;
import com.tokopakde.toryment.dto.product.ProductResDTO;
import com.tokopakde.toryment.dto.product.UpdateProductReqDTO;
import com.tokopakde.toryment.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageRes<ProductResDTO>> getSuppliers(@RequestParam(defaultValue = "0") Integer page,
                                                               @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageRes<ProductResDTO> pages = productService.getProducts(pageable);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResDTO> getSupplierById(@PathVariable String id) {
        var response = productService.getProductById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResDTO> createSupplier(@Valid @RequestBody CreateProductReqDTO request) {
        var response = productService.createProduct(request);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResDTO> updateSupplier(@PathVariable String id,
                                                       @Valid @RequestBody UpdateProductReqDTO request) {
        var response = productService.updateProduct(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResDTO> deleteSupplier(@PathVariable String id) {
        var response = productService.deleteProduct(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
