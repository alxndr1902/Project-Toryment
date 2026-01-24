package com.tokopakde.toryment.mapper;

import com.tokopakde.toryment.dto.category.CategoryResDTO;
import com.tokopakde.toryment.dto.category.CreateCategoryReqDTO;
import com.tokopakde.toryment.dto.category.UpdateCategoryReqDTO;
import com.tokopakde.toryment.model.company.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResDTO mapToDto(Category category);

    Category mapToEntity(CreateCategoryReqDTO dto);

    @Mapping(target = "version", ignore = true)
    Category updateEntity(UpdateCategoryReqDTO dto);
}
