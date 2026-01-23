package com.tokopakde.toryment.model.company;

import com.tokopakde.toryment.model.BaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "histories")
public class History extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "history_status", nullable = false)
    private HistoryStatus historyStatus;

    @Column(name = "date", nullable = false)
    private LocalDateTime dateTime;
}
