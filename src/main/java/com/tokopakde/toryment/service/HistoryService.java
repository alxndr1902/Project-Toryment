package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.history.HistoryResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryService {
    Page<HistoryResDTO> getHistories(Pageable pageable);

    HistoryResDTO getHistoryById(String id);
}
