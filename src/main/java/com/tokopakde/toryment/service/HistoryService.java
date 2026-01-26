package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.history.HistoryResDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import org.springframework.data.domain.Pageable;

public interface HistoryService {
    PageRes<HistoryResDTO> getHistories(Pageable pageable);

    HistoryResDTO getHistoryById(String id);
}
