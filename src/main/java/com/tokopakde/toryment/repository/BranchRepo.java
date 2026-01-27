package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BranchRepo extends JpaRepository<Branch, UUID> {
    Page<Branch> findAllBy(Pageable pageable);

    boolean existsByCode(String code);

    boolean existsByPhoneNumber(String phoneNumber);
}
