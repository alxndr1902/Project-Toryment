package com.tokopakde.toryment.service.impl;

import com.tokopakde.toryment.constant.Message;
import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.category.CategoryResDTO;
import com.tokopakde.toryment.dto.category.CreateCategoryReqDTO;
import com.tokopakde.toryment.dto.category.UpdateCategoryReqDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.exceptiohandler.exception.ConflictException;
import com.tokopakde.toryment.exceptiohandler.exception.OptimisticLockException;
import com.tokopakde.toryment.exceptiohandler.exception.DuplicateException;
import com.tokopakde.toryment.exceptiohandler.exception.NotFoundException;
import com.tokopakde.toryment.mapper.CategoryMapper;
import com.tokopakde.toryment.mapper.PageMapper;
import com.tokopakde.toryment.model.company.Category;
import com.tokopakde.toryment.repository.CategoryRepo;
import com.tokopakde.toryment.repository.ProductRepo;
import com.tokopakde.toryment.service.BaseService;
import com.tokopakde.toryment.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl extends BaseService implements CategoryService{
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final CategoryMapper mapper;
    private final PageMapper pageMapper;

    @Override
    public PageRes<CategoryResDTO> getCategories(Pageable pageable) {
        Page<Category> categories = categoryRepo.findAll(pageable);
        return pageMapper.toPageResponse(categories, mapper::mapToDto);
    }

    @Override
    public CategoryResDTO getCategory(String id) {
        var category = findCategoryById(id);
        return mapper.mapToDto(category);
    }

    @Override
    public CreateResDTO createCategory(CreateCategoryReqDTO request) {
        if (categoryRepo.existsByCode(request.getCode())) {
            throw new DuplicateException("This Code Is Used By Another Category");
        }

        var category = mapper.mapToEntity(request);
        var savedCategory = categoryRepo.save(prepareCreate(category));
        return new CreateResDTO(savedCategory.getId(), Message.CREATED.getDescription());
    }

    @Override
    public UpdateResDTO updateCategory(String id, UpdateCategoryReqDTO request) {
        var category = findCategoryById(id);

        if (!category.getVersion().equals(request.getVersion())) {
            throw new OptimisticLockException("Error Updating Category, Please Refresh The Page");
        }

        if (!category.getCode().equals(request.getCode())
                && categoryRepo.existsByCode(request.getCode())) {
            throw new DuplicateException("This Code Is Used By Another Category");
        }

        category.setCode(request.getCode());
        category.setName(request.getName());
        var updatedCategory = categoryRepo.saveAndFlush(prepareUpdate(category));
        return new UpdateResDTO(updatedCategory.getVersion(), Message.UPDATED.getDescription());
    }

    @Override
    public CommonResDTO deleteCategory(String id) {
        var category = findCategoryById(id);

        if (productRepo.existsByCategory(category)) {
            throw new ConflictException("Category Cannot Be Deleted, Because It Is Used By Existing Product");
        }

        categoryRepo.delete(category);
        return new CommonResDTO(Message.DELETED.getDescription());
    }

    private Category findCategoryById(String id) {
        var categoryId = parseUUID(id);
        return categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category Not Found"));
    }
}
