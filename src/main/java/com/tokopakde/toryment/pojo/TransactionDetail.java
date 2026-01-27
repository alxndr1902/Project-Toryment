package com.tokopakde.toryment.pojo;

import com.tokopakde.toryment.model.company.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDetail {
    private Product product;
    private Integer quantity;
}
