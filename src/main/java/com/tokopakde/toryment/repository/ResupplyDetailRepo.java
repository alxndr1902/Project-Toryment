package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.transaction.Resupply;
import com.tokopakde.toryment.model.transaction.ResupplyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResupplyDetailRepo extends JpaRepository<ResupplyDetail, UUID> {
    List<ResupplyDetail> findAllByResupply(Resupply resupply);
}
