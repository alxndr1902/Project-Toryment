package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.checkout.CheckoutDetailResDTO;
import com.tokopakde.toryment.dto.checkout.CheckoutResDTO;
import com.tokopakde.toryment.dto.checkout.CreateCheckoutReqDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CheckoutService {
    PageRes<CheckoutResDTO> getCheckouts(Pageable pageable);

    List<CheckoutDetailResDTO> getCheckout(String id);

    CreateResDTO createCheckout(CreateCheckoutReqDTO request);
}
