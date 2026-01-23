package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    Page<Supplier> findAll(Pageable pageable);
}
