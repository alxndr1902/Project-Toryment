package com.tokopakde.toryment.repository;

import com.tokopakde.toryment.model.transaction.Checkout;
import com.tokopakde.toryment.model.transaction.CheckoutDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CheckoutDetailRepo extends JpaRepository<CheckoutDetail, UUID> {
    List<CheckoutDetail> findAllByCheckout(Checkout checkout);
}
