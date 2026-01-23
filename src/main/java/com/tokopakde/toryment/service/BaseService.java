package com.tokopakde.toryment.service;

import com.tokopakde.toryment.exceptiohandler.exception.InvalidUUIDException;
import com.tokopakde.toryment.model.BaseModel;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class BaseService {
    protected <T extends BaseModel> T prepareCreate(T model) {
        model.setId(UUID.randomUUID());
        model.setCreatedAt(LocalDateTime.now());
        return model;
    }

    protected <T extends BaseModel> T prepareUpdate(T model) {
        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }

    protected UUID convertToUUID(String request) {
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
}
