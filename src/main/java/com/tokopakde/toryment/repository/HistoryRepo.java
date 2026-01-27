package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.dto.history.HistoryResDTO;
import com.tokopakde.toryment.model.company.History;
import com.tokopakde.toryment.model.company.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HistoryRepo extends JpaRepository<History, UUID> {
    Page<History> findAllBy(Pageable pageable);

    List<History> findAllByProduct(Product product);

    boolean existsByProduct(Product product);
}
