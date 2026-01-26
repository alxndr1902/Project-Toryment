package com.tokopakde.toryment.service.impl;

import com.tokopakde.toryment.constant.HistoryStatusCode;
import com.tokopakde.toryment.constant.Message;
import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.ProductQuantityDTO;
import com.tokopakde.toryment.dto.checkout.CheckoutDetailResDTO;
import com.tokopakde.toryment.dto.checkout.CheckoutResDTO;
import com.tokopakde.toryment.dto.checkout.CreateCheckoutReqDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.exceptiohandler.exception.InsufficientStockException;
import com.tokopakde.toryment.exceptiohandler.exception.NotFoundException;
import com.tokopakde.toryment.mapper.CheckoutMapper;
import com.tokopakde.toryment.mapper.PageMapper;
import com.tokopakde.toryment.model.company.History;
import com.tokopakde.toryment.model.company.Product;
import com.tokopakde.toryment.model.transaction.Checkout;
import com.tokopakde.toryment.model.transaction.CheckoutDetail;
import com.tokopakde.toryment.model.transaction.ResupplyDetail;
import com.tokopakde.toryment.pojo.TransactionDetail;
import com.tokopakde.toryment.repository.*;
import com.tokopakde.toryment.service.BaseService;
import com.tokopakde.toryment.service.CheckoutService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl extends BaseService implements CheckoutService {
    private final CheckoutRepo checkoutRepo;
    private final CheckoutDetailRepo checkoutDetailRepo;
    private final BranchRepo branchRepo;
    private final ProductRepo productRepo;
    private final HistoryRepo historyRepo;
    private final HistoryStatusRepo historyStatusRepo;
    private final CheckoutMapper checkoutMapper;
    private final PageMapper pageMapper;

    @Override
    public PageRes<CheckoutResDTO> getCheckouts(Pageable pageable) {
        Page<Checkout> checkouts = checkoutRepo.findAllBy(pageable);
        return pageMapper.toPageResponse(checkouts, checkoutMapper::mapToDto);
    }

    @Override
    public List<CheckoutDetailResDTO> getCheckout(String id) {
        var checkout = findCheckoutById(id);
        List<CheckoutDetail> details = checkoutDetailRepo.findAllByCheckout(checkout);
        return details.stream()
                .map(checkoutMapper::mapDetailToDto)
                .toList();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public CreateResDTO createCheckout(CreateCheckoutReqDTO request) {
        var now = LocalDateTime.now();

        var branchId = parseUUID(request.getBranchId());
        var branch = branchRepo.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch not found"));

        List<CheckoutDetail> checkoutDetails = prepareCheckoutDetail(request.getProducts(), now);

        var checkout = new Checkout();
        checkout.setCode(generateRandomAlphaNumeric());
        checkout.setDateTime(now);
        checkout.setBranch(branch);
        var savedCheckout = checkoutRepo.save(prepareCreate(checkout, now));

        for (CheckoutDetail detail : checkoutDetails) {
            detail.setCheckout(savedCheckout);
        }

        List<CheckoutDetail> savedDetails = checkoutDetailRepo.saveAll(checkoutDetails);
        updateStock(savedDetails, now);
        createHistory(savedDetails, now);

        return new CreateResDTO(savedCheckout.getId(), Message.CREATED.getDescription());
    }

    private List<CheckoutDetail> prepareCheckoutDetail(List<ProductQuantityDTO> request,
                                                       LocalDateTime now) {
        List<TransactionDetail> transactionDetails = prepareDetails(request, productRepo);

        List<CheckoutDetail> details = new ArrayList<>();
        for (TransactionDetail detail : transactionDetails) {
            var product = detail.getProduct();
            var quantity = detail.getQuantity();

            if (product.getStock() < quantity) {
                throw new InsufficientStockException(
                        product.getName() + " Has Insufficient Stock ( " + product.getStock() + " is remaining)");
            }

            var checkoutDetail = new CheckoutDetail();
            checkoutDetail.setProduct(product);
            checkoutDetail.setQuantity(quantity);

            details.add(prepareCreate(checkoutDetail, now));
        }

        return details;
    }

    private void updateStock(List<CheckoutDetail> savedDetails, LocalDateTime now) {
        for (CheckoutDetail detail : savedDetails) {
            productRepo.checkoutStock(detail.getProduct().getId(),
                    detail.getQuantity(), now);
        }
    }

    private void createHistory(List<CheckoutDetail> savedDetails,
                               LocalDateTime now) {
        var status = historyStatusRepo.findByCode(HistoryStatusCode.CO.name())
                .orElseThrow(() -> new NotFoundException("History Status Is Not Found"));
        List<History> historyStatus = new ArrayList<>();
        for (CheckoutDetail detail : savedDetails) {
            var history = new History();
            history.setProduct(detail.getProduct());
            history.setQuantity(detail.getQuantity());
            history.setHistoryStatus(status);
            historyStatus.add(prepareCreate(history, now));
        }

        historyRepo.saveAll(historyStatus);
    }

    private Checkout findCheckoutById(String id) {
        var checkoutId = parseUUID(id);
        return checkoutRepo.findById(checkoutId)
                .orElseThrow(() -> new NotFoundException("Checkout Data Not Found"));
    }
}
