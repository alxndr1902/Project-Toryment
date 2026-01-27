package com.tokopakde.toryment.controller;

import com.tokopakde.toryment.dto.CreateTransactionResDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.dto.resupply.CreateResupplyReqDTO;
import com.tokopakde.toryment.dto.resupply.ResupplyDetailResDTO;
import com.tokopakde.toryment.dto.resupply.ResupplyResDTO;
import com.tokopakde.toryment.service.ResupplyService;
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
@RequestMapping("resupplies")
public class ResupplyController {
    private final ResupplyService resupplyService;

    @GetMapping
    public ResponseEntity<PageRes<ResupplyResDTO>> getSuppliers(@RequestParam(defaultValue = "0") Integer page,
                                                                @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageRes<ResupplyResDTO> pages = resupplyService.getResupplies(pageable);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<ResupplyDetailResDTO>> getSupplierById(@PathVariable String id) {
        List<ResupplyDetailResDTO> response = resupplyService.getResupplyById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateTransactionResDTO> createSupplier(@Valid @RequestBody CreateResupplyReqDTO request) {
        var response = resupplyService.createResupply(request);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
