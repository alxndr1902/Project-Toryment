package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {
    Page<Category> findAllBy(Pageable pageable);

    boolean existsByCode(String code);

    Optional<Category> findByCode(String code);
}
