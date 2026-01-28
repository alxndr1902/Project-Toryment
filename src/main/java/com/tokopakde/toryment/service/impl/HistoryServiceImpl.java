package com.tokopakde.toryment.service.impl;

import com.tokopakde.toryment.dto.history.HistoryResDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.exceptiohandler.exception.NotFoundException;
import com.tokopakde.toryment.mapper.HistoryMapper;
import com.tokopakde.toryment.mapper.PageMapper;
import com.tokopakde.toryment.model.company.History;
import com.tokopakde.toryment.repository.HistoryRepo;
import com.tokopakde.toryment.repository.ProductRepo;
import com.tokopakde.toryment.service.BaseService;
import com.tokopakde.toryment.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class HistoryServiceImpl extends BaseService implements HistoryService {
    private final HistoryRepo historyRepo;
    private final ProductRepo productRepo;
    private final HistoryMapper historyMapper;
    private final PageMapper pageMapper;

    @Override
    public PageRes<HistoryResDTO> getHistories(Pageable pageable) {
        Page<History> histories = historyRepo.findAll(pageable);
        return pageMapper.toPageResponse(histories, historyMapper::mapToDto);
    }

    @Override
    public List<HistoryResDTO> getHistoryByProductId(String id) {
        UUID productId = parseUUID(id);
        var product = productRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        var histories = historyRepo.findAllByProduct(product);
        return histories.stream()
                .map(historyMapper::mapToDto)
                .toList();
    }
}
