package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.ProductQuantityDTO;
import com.tokopakde.toryment.exceptiohandler.exception.DuplicateException;
import com.tokopakde.toryment.exceptiohandler.exception.InvalidUUIDException;
import com.tokopakde.toryment.exceptiohandler.exception.NotFoundException;
import com.tokopakde.toryment.model.BaseModel;
import com.tokopakde.toryment.model.company.Product;
import com.tokopakde.toryment.pojo.TransactionDetail;
import com.tokopakde.toryment.repository.ProductRepo;

import java.time.LocalDateTime;
import java.util.*;

public class BaseService {
    protected <T extends BaseModel> T prepareCreate(T model) {
        model.setId(UUID.randomUUID());
        model.setCreatedAt(LocalDateTime.now());
        return model;
    }

    protected <T extends BaseModel> T prepareCreate(T model, LocalDateTime now) {
        model.setId(UUID.randomUUID());
        model.setCreatedAt(now);
        return model;
    }

    protected <T extends BaseModel> T prepareUpdate(T model) {
        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }

    protected UUID parseUUID(String request) {
        if (request == null || request.isBlank()) {
            throw new InvalidUUIDException("Id Is Required");
        }
        try {
            return UUID.fromString(request);
        } catch (IllegalArgumentException e) {
            throw new InvalidUUIDException("Invalid UUID");
        }
    }

    protected String generateRandomAlphaNumeric() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(chars.length());
            result.append(chars.charAt(index));
        }
        return result.toString();
    }

    protected List<TransactionDetail> prepareDetails(List<ProductQuantityDTO> requests,
                                                     ProductRepo productRepo) {
        Map<UUID, Integer> idQuantity = new HashMap<>();
        for (var dto : requests) {
            var productId = parseUUID(dto.getProductId());

            if (idQuantity.containsKey(productId)) {
                throw new DuplicateException("Duplicate Product");
            }

            idQuantity.put(productId, dto.getQuantity());
        }

        List<UUID> productIds = new ArrayList<>(idQuantity.keySet());

        List<Product> products = productRepo.findAllById(productIds);

        if (products.size() != productIds.size()) {
            throw new NotFoundException("Products Are Not Found");
        }

        List<TransactionDetail> txDetails = products.stream()
                .map(product -> new TransactionDetail(product,
                        idQuantity.get(product.getId())))
                .toList();

        return txDetails;
    }
}
