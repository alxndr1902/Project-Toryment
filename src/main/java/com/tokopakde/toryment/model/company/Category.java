package com.tokopakde.toryment.model.company;

import com.tokopakde.toryment.model.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Email
@Table(name = "categories")
public class Category extends BaseModel {
    @Column(nullable = false, unique = true, length = 20)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;
}
