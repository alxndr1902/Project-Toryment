package com.tokopakde.toryment.controller;

import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.branch.BranchResDTO;
import com.tokopakde.toryment.dto.branch.CreateBranchReqDTO;
import com.tokopakde.toryment.dto.branch.UpdateBranchReqDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.service.BranchService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("branches")
public class BranchController {
    private final BranchService branchService;

    @GetMapping
    public ResponseEntity<PageRes<BranchResDTO>> getBranches(@RequestParam(defaultValue = "0") @Min(0) Integer page,
                                                              @RequestParam(defaultValue = "10") @Min(value = 1) Integer size,
                                                              @RequestParam(required = false) @Size(max = 100) String branchName,
                                                              @RequestParam(defaultValue = "id")String sortBy,
                                                              @RequestParam(defaultValue = "true")boolean ascending) {

        PageRes<BranchResDTO> pages = branchService.getBranches(page, size, branchName, sortBy, ascending);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BranchResDTO> getBranchById(@PathVariable String id) {
        var response = branchService.getBranchById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResDTO> createBranch(@Valid @RequestBody CreateBranchReqDTO request) {
        var response = branchService.createBranch(request);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResDTO> updateBranch(@PathVariable String id,
                                                       @Valid @RequestBody UpdateBranchReqDTO request) {
        var response = branchService.updateBranch(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResDTO> deleteBranch(@PathVariable String id) {
        var response = branchService.deleteBranch(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
