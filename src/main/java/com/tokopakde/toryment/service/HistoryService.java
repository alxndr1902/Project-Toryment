package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.history.HistoryResDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HistoryService {
    PageRes<HistoryResDTO> getHistories(Pageable pageable);

    List<HistoryResDTO> getHistoryByProductId(String id);
}
