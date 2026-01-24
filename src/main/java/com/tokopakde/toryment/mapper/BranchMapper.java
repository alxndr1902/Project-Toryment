package com.tokopakde.toryment.mapper;

import com.tokopakde.toryment.dto.branch.BranchResDTO;
import com.tokopakde.toryment.dto.branch.CreateBranchReqDTO;
import com.tokopakde.toryment.dto.branch.UpdateBranchReqDTO;
import com.tokopakde.toryment.model.company.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchResDTO mapToDto(Branch branch);

    Branch mapToEntity(CreateBranchReqDTO dto);

    @Mapping(target = "version", ignore = true)
    Branch updateEntity(UpdateBranchReqDTO dto);
}
