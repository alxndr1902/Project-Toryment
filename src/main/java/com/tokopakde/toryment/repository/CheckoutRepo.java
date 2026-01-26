package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.transaction.Checkout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CheckoutRepo extends JpaRepository<Checkout, UUID> {
    Page<Checkout> findAllBy(Pageable pageable);
}
