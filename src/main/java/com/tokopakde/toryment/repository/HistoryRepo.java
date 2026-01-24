package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoryRepo extends JpaRepository<History, UUID> {
    Page<History> findAllBy(Pageable pageable);
}
