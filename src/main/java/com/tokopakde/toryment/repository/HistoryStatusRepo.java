package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.company.HistoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoryStatusRepo extends JpaRepository<HistoryStatus, UUID> {
}
