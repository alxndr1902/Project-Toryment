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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/histories")
    public ResponseEntity<PageRes<HistoryResDTO>> getHistories(@RequestParam(defaultValue = "0") Integer page,
                                                               @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageRes<HistoryResDTO> pages = historyService.getHistories(pageable);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @GetMapping("{id}/histories")
    public ResponseEntity<List<HistoryResDTO>> getHistoriesByProductId(@PathVariable String id) {
        var response = historyService.getHistoryByProductId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
