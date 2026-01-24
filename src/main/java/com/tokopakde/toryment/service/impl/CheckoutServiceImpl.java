package com.tokopakde.toryment.service.impl;

import com.tokopakde.toryment.constant.Message;
import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.ProductQuantityDTO;
import com.tokopakde.toryment.dto.checkout.CheckoutResDTO;
import com.tokopakde.toryment.dto.checkout.CreateCheckoutReqDTO;
import com.tokopakde.toryment.exceptiohandler.exception.InsufficientStockException;
import com.tokopakde.toryment.exceptiohandler.exception.NotFoundException;
import com.tokopakde.toryment.mapper.CheckoutMapper;
import com.tokopakde.toryment.model.company.Product;
import com.tokopakde.toryment.model.transaction.Checkout;
import com.tokopakde.toryment.model.transaction.CheckoutDetail;
import com.tokopakde.toryment.repository.BranchRepo;
import com.tokopakde.toryment.repository.CheckoutDetailRepo;
import com.tokopakde.toryment.repository.CheckoutRepo;
import com.tokopakde.toryment.repository.ProductRepo;
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
    private final CheckoutMapper mapper;

    @Override
    public Page<CheckoutResDTO> getCheckouts(Pageable pageable) {
        Page<Checkout> checkouts = checkoutRepo.findAllBy(pageable);
        return checkouts.map(mapper::mapToDto);
    }

    @Override
    public CheckoutResDTO getCheckout(String id) {
        var checkout = findCheckoutById(id);
        return mapper.mapToDto(checkout);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public CreateResDTO createCheckout(CreateCheckoutReqDTO request) {
        LocalDateTime now = LocalDateTime.now();

        var branchId = convertToUUID(request.getBranchId());
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
        updateStock(savedDetails);

        return new CreateResDTO(savedCheckout.getId(), Message.CREATED.getDescription());
    }

    private List<CheckoutDetail> prepareCheckoutDetail(List<ProductQuantityDTO> request,
                                      LocalDateTime now) {
        Map<UUID, Integer> requestedQuantity = request.stream()
                .collect(Collectors.toMap(
                        dto -> convertToUUID(dto.getProductId()),
                        ProductQuantityDTO::getQuantity,
                        Integer::sum)
                );

        Map<UUID, Product> products = productRepo.findAllById(requestedQuantity.keySet()).stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        if (products.size() != requestedQuantity.size()) {
            throw new NotFoundException("Products Are Not Found");
        }

        List<CheckoutDetail> details = new ArrayList<>();

        for (Map.Entry<UUID, Integer> entry : requestedQuantity.entrySet()) {
            var product = products.get(entry.getKey());
            var quantity = entry.getValue();

            if (product.getStock() < quantity) {
                throw new InsufficientStockException(product.getName() + " Has Insufficient Stock");
            }

            var detail = new CheckoutDetail();
            detail.setProduct(product);
            detail.setQuantity(quantity);

            details.add(prepareCreate(detail, now));
        }

        return details;
    }

    private void updateStock(List<CheckoutDetail> savedDetails) {
        for (CheckoutDetail detail : savedDetails) {
            var product = detail.getProduct();
            product.setStock(product.getStock() - detail.getQuantity());
            productRepo.save(product);
        }
        productRepo.saveAll(savedDetails.stream()
                .map(CheckoutDetail::getProduct)
                .toList());
    }

    @Override
    public CommonResDTO deleteCheckout(String id) {
        var checkout = findCheckoutById(id);
        checkoutRepo.delete(checkout);
        return new CommonResDTO(Message.DELETED.getDescription());
    }

    private Checkout findCheckoutById(String id) {
        var checkoutId = convertToUUID(id);
        return checkoutRepo.findById(checkoutId)
                .orElseThrow(() -> new NotFoundException("Checkout Data Not Found"));
    }
}
