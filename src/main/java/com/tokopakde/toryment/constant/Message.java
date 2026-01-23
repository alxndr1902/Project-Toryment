package com.tokopakde.toryment.constant;

public enum Message {
    CREATED("Data Created"),
    UPDATED("Data Updated"),
    DELETED("Data Deleted");

    private final String name;

    Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
