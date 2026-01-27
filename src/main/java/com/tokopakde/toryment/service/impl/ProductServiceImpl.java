package com.tokopakde.toryment.service.impl;

import com.tokopakde.toryment.constant.Message;
import com.tokopakde.toryment.dto.CommonResDTO;
import com.tokopakde.toryment.dto.CreateResDTO;
import com.tokopakde.toryment.dto.UpdateResDTO;
import com.tokopakde.toryment.dto.pagination.PageRes;
import com.tokopakde.toryment.dto.product.CreateProductReqDTO;
import com.tokopakde.toryment.dto.product.ProductResDTO;
import com.tokopakde.toryment.dto.product.UpdateProductReqDTO;
import com.tokopakde.toryment.exceptiohandler.exception.ConflictException;
import com.tokopakde.toryment.exceptiohandler.exception.DataIntegrationException;
import com.tokopakde.toryment.exceptiohandler.exception.DuplicateException;
import com.tokopakde.toryment.exceptiohandler.exception.NotFoundException;
import com.tokopakde.toryment.mapper.PageMapper;
import com.tokopakde.toryment.mapper.ProductMapper;
import com.tokopakde.toryment.model.company.Product;
import com.tokopakde.toryment.repository.CategoryRepo;
import com.tokopakde.toryment.repository.HistoryRepo;
import com.tokopakde.toryment.repository.ProductRepo;
import com.tokopakde.toryment.service.BaseService;
import com.tokopakde.toryment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl extends BaseService implements ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final HistoryRepo historyRepo;
    private final ProductMapper productMapper;
    private final PageMapper pageMapper;

    @Override
    public PageRes<ProductResDTO> getProducts(Pageable pageable) {
        Page<Product> products = productRepo.findAllBy(pageable);
        return pageMapper.toPageResponse(products, productMapper::mapToDto);
    }

    @Override
    public ProductResDTO getProductById(String id) {
        var product = findProductById(id);
        return productMapper.mapToDto(product);
    }

    @Override
    public CreateResDTO createProduct(CreateProductReqDTO request) {
        if (productRepo.existsByCode(request.getCode())) {
            throw new DuplicateException("This Code Is Used By Another Product");
        }

        var categoryId = parseUUID(request.getCategoryId());
        var category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category Is Not Found"));

        var product = productMapper.mapToEntity(request);
        product.setCategory(category);

        var savedProduct = productRepo.save(prepareCreate(product));

        return new CreateResDTO(savedProduct.getId(), Message.CREATED.getDescription());
    }

    @Override
    public UpdateResDTO updateProduct(String id, UpdateProductReqDTO request) {
        var product = findProductById(id);

        if (!product.getVersion().equals(request.getVersion())) {
            throw new DataIntegrationException("Error Updating Product, Please Refresh The Page");
        }

        if (!product.getCode().equals(request.getCode())
                && productRepo.existsByCode(request.getCode())) {
            throw new DuplicateException("This Code Is Used By Another Product");
        }

        product.setCode(request.getCode());
        product.setName(request.getName());
        var updatedProduct = productRepo.saveAndFlush(prepareUpdate(product));
        return new UpdateResDTO(updatedProduct.getVersion(), Message.UPDATED.getDescription());
    }

    @Override
    public CommonResDTO deleteProduct(String id) {
        var product = findProductById(id);

        if (historyRepo.existsByProduct(product)) {
            throw new ConflictException("Product Cannot Be Deleted, Because The Product Has Transaction Histories");
        }

        productRepo.delete(product);
        return new CommonResDTO(Message.DELETED.getDescription());
    }

    private Product findProductById(String id) {
        var productId = parseUUID(id);
        return productRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product Is Not Found"));
    }
}
