package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepo extends JpaRepository<Supplier, UUID> {
    Page<Supplier> findAllBy(Pageable pageable);

    boolean existsByName(String name);

    boolean existsByPhoneNumber(String phoneNumber);
}
