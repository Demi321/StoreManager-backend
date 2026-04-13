package com.sm.storagemanager.productcategory.service.impl;

import com.sm.storagemanager.branch.entity.Branch;
import com.sm.storagemanager.productcategory.dto.ProductCategoryDto;
import com.sm.storagemanager.productcategory.entity.ProductCategory;
import com.sm.storagemanager.productcategory.repository.ProductCategoryRepository;
import com.sm.storagemanager.productcategory.service.ProductCategoryService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl
        extends AbstractCrudService<ProductCategory, ProductCategoryDto, Long>
        implements ProductCategoryService {

    public ProductCategoryServiceImpl(ProductCategoryRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected ProductCategoryDto toDto(ProductCategory entity) {
        return new ProductCategoryDto(
                entity.getId(),
                entity.getBranch().getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected ProductCategory toNewEntity(ProductCategoryDto dto) {
        ProductCategory entity = new ProductCategory();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(ProductCategory entity, ProductCategoryDto dto) {
        entity.setBranch(getReference(Branch.class, dto.getBranchId()));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }
}

