package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.transaction.Resupply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResupplyRepo extends JpaRepository<Resupply, UUID> {
}
