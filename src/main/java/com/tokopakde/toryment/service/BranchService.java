package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.branch.BranchResDTO;
import com.tokopakde.toryment.dto.branch.CreateBranchReqDTO;
import com.tokopakde.toryment.dto.branch.UpdateBranchReqDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import org.springframework.data.domain.Pageable;

public interface BranchService {
    PageRes<BranchResDTO> getBranches(Pageable pageable);

    BranchResDTO getBranchById(String id);

    CreateResDTO createBranch(CreateBranchReqDTO request);

    UpdateResDTO updateBranch(String id, UpdateBranchReqDTO request);

    CommonResDTO deleteBranch(String id);
}
