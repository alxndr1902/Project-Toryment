package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.dto.resupply.CreateResupplyReqDTO;
import com.tokopakde.toryment.dto.resupply.ResupplyDetailResDTO;
import com.tokopakde.toryment.dto.resupply.ResupplyResDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResupplyService {
    PageRes<ResupplyResDTO> getResupplies(Pageable pageable);

    List<ResupplyDetailResDTO> getResupplyById(String id);

    CreateResDTO createResupply(CreateResupplyReqDTO request);
}
