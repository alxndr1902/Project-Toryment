package com.tokopakde.toryment.service;

import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.category.CategoryResDTO;
import com.tokopakde.toryment.dto.category.CreateCategoryReqDTO;
import com.tokopakde.toryment.dto.category.UpdateCategoryReqDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    PageRes<CategoryResDTO> getCategories(Pageable pageable);

    CategoryResDTO getCategory(String id);

    CreateResDTO createCategory(CreateCategoryReqDTO request);

    UpdateResDTO updateCategory(String id, UpdateCategoryReqDTO request);

    CommonResDTO deleteCategory(String id);
}
