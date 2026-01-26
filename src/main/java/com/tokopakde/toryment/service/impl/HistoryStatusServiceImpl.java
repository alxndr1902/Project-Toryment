package com.tokopakde.toryment.service.impl;

import com.tokopakde.toryment.dto.history.HistoryStatusResDTO;
import com.tokopakde.toryment.exceptiohandler.exception.NotFoundException;
import com.tokopakde.toryment.mapper.HistoryStatusMapper;
import com.tokopakde.toryment.repository.HistoryStatusRepo;
import com.tokopakde.toryment.service.BaseService;
import com.tokopakde.toryment.service.HistoryStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class HistoryStatusServiceImpl extends BaseService implements HistoryStatusService {
    private final HistoryStatusRepo  historyStatusRepo;
    private final HistoryStatusMapper historyStatusMapper;
    @Override
    public List<HistoryStatusResDTO> getHistoryStatus() {
        return historyStatusRepo.findAll().stream()
                .map(historyStatusMapper::toDto)
                .toList();
    }

    @Override
    public HistoryStatusResDTO getHistoryStatusById(String id) {
        UUID statusId = parseUUID(id);
        var historyStatus = historyStatusRepo.findById(statusId)
                .orElseThrow(() -> new NotFoundException("History Status Not Found"));
        return historyStatusMapper.toDto(historyStatus);
    }
}
