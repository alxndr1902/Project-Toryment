package com.tokopakde.toryment.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class BranchResDTO {
    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private Integer version;
}
