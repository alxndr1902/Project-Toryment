package com.tokopakde.toryment.controller;

import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.checkout.CheckoutDetailResDTO;
import com.tokopakde.toryment.dto.checkout.CheckoutResDTO;
import com.tokopakde.toryment.dto.checkout.CreateCheckoutReqDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.model.transaction.CheckoutDetail;
import com.tokopakde.toryment.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("checkouts")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @GetMapping
    public ResponseEntity<PageRes<CheckoutResDTO>> getSuppliers(@RequestParam(defaultValue = "0") Integer page,
                                                                @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageRes<CheckoutResDTO> pages = checkoutService.getCheckouts(pageable);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<CheckoutDetailResDTO>> getSupplierById(@PathVariable String id) {
        List<CheckoutDetailResDTO> response = checkoutService.getCheckout(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResDTO> createSupplier(@Valid @RequestBody CreateCheckoutReqDTO request) {
        var response = checkoutService.createCheckout(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
