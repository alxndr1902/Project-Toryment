package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.history.HistoryStatusResDTO;

import java.util.List;

public interface HistoryStatusService {
    List<HistoryStatusResDTO> getHistoryStatus();

    HistoryStatusResDTO getHistoryStatusById(String id);
}
