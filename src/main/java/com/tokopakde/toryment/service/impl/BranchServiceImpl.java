package com.tokopakde.toryment.service.impl;

import com.tokopakde.toryment.constant.Message;
import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.branch.BranchResDTO;
import com.tokopakde.toryment.dto.branch.CreateBranchReqDTO;
import com.tokopakde.toryment.dto.branch.UpdateBranchReqDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.exceptiohandler.exception.OptimisticLockException;
import com.tokopakde.toryment.exceptiohandler.exception.DuplicateException;
import com.tokopakde.toryment.exceptiohandler.exception.NotFoundException;
import com.tokopakde.toryment.mapper.BranchMapper;
import com.tokopakde.toryment.mapper.PageMapper;
import com.tokopakde.toryment.model.company.Branch;
import com.tokopakde.toryment.repository.BranchRepo;
import com.tokopakde.toryment.service.BaseService;
import com.tokopakde.toryment.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BranchServiceImpl extends BaseService implements BranchService {
    private final BranchRepo branchRepo;
    private final BranchMapper branchMapper;
    private final PageMapper pageMapper;

    @Override
    public PageRes<BranchResDTO> getBranches(Pageable pageable) {
        Page<Branch> branches = branchRepo.findAll(pageable);
        return pageMapper.toPageResponse(branches, branchMapper::mapToDto);
    }

    @Override
    public BranchResDTO getBranchById(String id) {
        var branch = findBranchById(id);
        return branchMapper.mapToDto(branch);
    }

    @Override
    public CreateResDTO createBranch(CreateBranchReqDTO request) {
        if (branchRepo.existsByCode(request.getCode())) {
            throw new DuplicateException("This Code Is Used By Another Branch");
        }

        if (branchRepo.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateException("This Phone Number Is Used By Another Branch");
        }

        var branch = branchMapper.mapToEntity(request);
        var savedBranch = branchRepo.save(prepareCreate(branch));
        return new CreateResDTO(savedBranch.getId(), Message.CREATED.getDescription());
    }

    @Override
    public UpdateResDTO updateBranch(String id, UpdateBranchReqDTO request) {
        var branch = findBranchById(id);

        if (!branch.getVersion().equals(request.getVersion())) {
            throw new OptimisticLockException("Error Updating Branch, Please Refresh The Page");
        }

        if (!branch.getCode().equals(request.getCode())
                && branchRepo.existsByCode(request.getCode())) {
            throw new DuplicateException("This Code Is Used By Another Branch");
        }

        if (!branch.getPhoneNumber().equals(request.getPhoneNumber())
                && branchRepo.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateException("This Phone Number Is Used By Another Branch");
        }

        branch.setCode(request.getCode());
        branch.setName(request.getName());
        branch.setAddress(request.getAddress());
        branch.setPhoneNumber(request.getPhoneNumber());
        var updatedBranch = branchRepo.saveAndFlush(prepareUpdate(branch));
        return new UpdateResDTO(updatedBranch.getVersion(), Message.UPDATED.getDescription());
    }

    @Override
    public CommonResDTO deleteBranch(String id) {
        var branch = findBranchById(id);
        branchRepo.delete(branch);
        return new CommonResDTO(Message.DELETED.getDescription());
    }

    private Branch findBranchById(String id) {
        UUID branchId = parseUUID(id);
        return branchRepo.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch Not Found"));
    }
}
