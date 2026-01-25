package com.tokopakde.toryment.model.transaction;

import com.tokopakde.toryment.model.BaseModel;
import com.tokopakde.toryment.model.company.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "resupply_details")
public class ResupplyDetail extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resupply_id", nullable = false)
    private Resupply resupply;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}
