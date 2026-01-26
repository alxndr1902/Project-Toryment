package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.HistoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HistoryStatusRepo extends JpaRepository<HistoryStatus, UUID> {
    Optional<HistoryStatus> findByCode(String code);
}
