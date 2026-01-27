package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {
    boolean existsByCode(String code);
}
