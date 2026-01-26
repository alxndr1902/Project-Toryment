package com.tokopakde.toryment.pojo;

import com.tokopakde.toryment.model.company.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDetail {
    private Product product;
    private Integer quantity;
}
