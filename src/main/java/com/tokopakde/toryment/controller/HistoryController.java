package com.tokopakde.toryment.controller;

import com.tokopakde.toryment.dto.history.HistoryResDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("histories")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<PageRes<HistoryResDTO>> getSuppliers(@RequestParam(defaultValue = "0") Integer page,
                                                               @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageRes<HistoryResDTO> pages = historyService.getHistories(pageable);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<HistoryResDTO> getSupplierById(@PathVariable String id) {
        var response = historyService.getHistoryById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
