package com.tokopakde.toryment.constant;

import lombok.Getter;

@Getter
public enum Message {
    CREATED("Data Created"),
    UPDATED("Data Updated"),
    DELETED("Data Deleted");

    private final String description;

    Message(String description) {
        this.description = description;
    }
}
