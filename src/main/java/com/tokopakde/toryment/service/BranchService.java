package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.branch.BranchResDTO;
import com.tokopakde.toryment.dto.branch.CreateBranchReqDTO;
import com.tokopakde.toryment.dto.branch.UpdateBranchReqDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BranchService {
    Page<BranchResDTO> getBranches(Pageable pageable);

    BranchResDTO getBranchById(String id);

    CreateResDTO createBranch(CreateBranchReqDTO request);

    UpdateResDTO updateBranch(String id, UpdateBranchReqDTO request);

    CommonResDTO deleteBranch(String id);
}
