package com.tokopakde.toryment.service.impl;

import com.tokopakde.toryment.constant.HistoryStatusCode;
import com.tokopakde.toryment.constant.Message;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.ProductQuantityDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.dto.resupply.CreateResupplyReqDTO;
import com.tokopakde.toryment.dto.resupply.ResupplyDetailResDTO;
import com.tokopakde.toryment.dto.resupply.ResupplyResDTO;
import com.tokopakde.toryment.exceptiohandler.exception.NotFoundException;
import com.tokopakde.toryment.mapper.PageMapper;
import com.tokopakde.toryment.mapper.ResupplyMapper;
import com.tokopakde.toryment.model.company.History;
import com.tokopakde.toryment.model.company.HistoryStatus;
import com.tokopakde.toryment.model.transaction.Resupply;
import com.tokopakde.toryment.model.transaction.ResupplyDetail;
import com.tokopakde.toryment.pojo.TransactionDetail;
import com.tokopakde.toryment.repository.*;
import com.tokopakde.toryment.service.BaseService;
import com.tokopakde.toryment.service.ResupplyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ResupplyServiceImpl extends BaseService implements ResupplyService {
    private final ResupplyRepo resupplyRepo;
    private final SupplierRepo supplierRepo;
    private final ResupplyDetailRepo resupplyDetailRepo;
    private final ProductRepo productRepo;
    private final HistoryStatusRepo historyStatusRepo;
    private final ResupplyMapper resupplyMapper;
    private final PageMapper pageMapper;
    private final HistoryRepo historyRepo;

    @Override
    public PageRes<ResupplyResDTO> getResupplies(Pageable pageable) {
        var resupplies = resupplyRepo.findAllBy(pageable);
        return pageMapper.toPageResponse(resupplies, resupplyMapper::mapToDto);
    }

    @Override
    public List<ResupplyDetailResDTO> getResupplyById(String id) {
        var resupply = findResupplyById(id);
        List<ResupplyDetail> details = resupplyDetailRepo.findAllByResupply(resupply);
        return details.stream()
                .map(resupplyMapper::mapDetailToDto)
                .toList();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public CreateResDTO createResupply(CreateResupplyReqDTO request) {
        LocalDateTime now = LocalDateTime.now();

        var supplierId = parseUUID(request.getSupplierId());
        var supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier not found"));

        List<ResupplyDetail> resupplyDetails = prepareResupplyDetails(request.getProducts(), now);

        var resupply = new Resupply();
        resupply.setCode(generateRandomAlphaNumeric());
        resupply.setDateTime(now);
        resupply.setSupplier(supplier);
        var savedResupply = resupplyRepo.save(prepareCreate(resupply, now));

        for (ResupplyDetail detail : resupplyDetails) {
            detail.setResupply(savedResupply);
        }

        List<ResupplyDetail> savedDetails = resupplyDetailRepo.saveAll(resupplyDetails);
        updateStock(savedDetails, now);
        createHistory(savedDetails, now);

        return new CreateResDTO(savedResupply.getId(), Message.CREATED.getDescription());
    }

    private List<ResupplyDetail> prepareResupplyDetails(List<ProductQuantityDTO> request,
                                                        LocalDateTime now) {
        List<TransactionDetail> transactionDetails = prepareDetails(request, productRepo);

        List<ResupplyDetail> details = new ArrayList<>();
        for (TransactionDetail detail : transactionDetails) {
            var product = detail.getProduct();
            var quantity = detail.getQuantity();
            var resupplyDetail = new ResupplyDetail();
            resupplyDetail.setProduct(product);
            resupplyDetail.setQuantity(quantity);

            details.add(prepareCreate(resupplyDetail, now));
        }

        return details;
    }

    private void updateStock(List<ResupplyDetail> savedDetails, LocalDateTime now) {
        for (ResupplyDetail detail : savedDetails) {
            productRepo.resupplyStock(detail.getProduct().getId(),
                    detail.getQuantity(), now);
        }
    }

    private void createHistory(List<ResupplyDetail> savedDetails,
                               LocalDateTime now) {
        var status = historyStatusRepo.findByCode(HistoryStatusCode.RESUP.name())
                .orElseThrow(() -> new NotFoundException("History Status Is Not Found"));
        List<History> historyStatus = new ArrayList<>();
        for (ResupplyDetail detail : savedDetails) {
            var history = new History();
            history.setProduct(detail.getProduct());
            history.setQuantity(detail.getQuantity());
            history.setHistoryStatus(status);
            historyStatus.add(prepareCreate(history, now));
        }

        historyRepo.saveAll(historyStatus);
    }

    private Resupply findResupplyById(String id) {
        var resupplyId = parseUUID(id);
        return resupplyRepo.findById(resupplyId)
                .orElseThrow(() -> new NotFoundException("Resupply Not Found"));
    }
}
