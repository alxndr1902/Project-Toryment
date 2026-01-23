package com.tokopakde.toryment.model.transaction;

import com.tokopakde.toryment.model.BaseModel;
import com.tokopakde.toryment.model.company.Branch;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "checkouts")
public class Checkout extends BaseModel {
    @Column(nullable = false, unique = true, length = 20)
    private String code;

    @Column
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
}
