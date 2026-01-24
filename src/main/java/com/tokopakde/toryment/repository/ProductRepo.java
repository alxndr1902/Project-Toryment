package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    Page<Product> findAllBy(Pageable pageable);
}
