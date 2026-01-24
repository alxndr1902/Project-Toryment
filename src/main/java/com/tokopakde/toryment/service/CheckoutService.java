package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.checkout.CheckoutResDTO;
import com.tokopakde.toryment.dto.checkout.CreateCheckoutReqDTO;
import com.tokopakde.toryment.dto.checkout.UpdateCheckoutReqDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CheckoutService {
    Page<CheckoutResDTO> getCheckouts(Pageable pageable);

    CheckoutResDTO getCheckout(String id);

    CreateResDTO createCheckout(CreateCheckoutReqDTO request);

    CommonResDTO deleteCheckout(String id);
}
