package com.tokopakde.toryment.controller;

import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.branch.BranchResDTO;
import com.tokopakde.toryment.dto.branch.CreateBranchReqDTO;
import com.tokopakde.toryment.dto.branch.UpdateBranchReqDTO;
import com.tokopakde.toryment.dto.history.HistoryStatusResDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.service.HistoryStatusService;
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
@RequestMapping("history-status")
public class HistoryStatusController {
    private final HistoryStatusService historyStatusService;

    @GetMapping
    public ResponseEntity<List<HistoryStatusResDTO>> getSuppliers() {
        List<HistoryStatusResDTO> pages = historyStatusService.getHistoryStatus();
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<HistoryStatusResDTO> getSupplierById(@PathVariable String id) {
        var response = historyStatusService.getHistoryStatusById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
