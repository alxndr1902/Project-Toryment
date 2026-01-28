package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.History;
import com.tokopakde.toryment.model.company.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HistoryRepo extends JpaRepository<History, UUID> {
    List<History> findAllByProduct(Product product);

    boolean existsByProduct(Product product);
}
