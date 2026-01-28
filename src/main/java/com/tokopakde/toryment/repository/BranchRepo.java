package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchRepo extends JpaRepository<Branch, UUID> {
    boolean existsByCode(String code);

    boolean existsByPhoneNumber(String phoneNumber);
}
