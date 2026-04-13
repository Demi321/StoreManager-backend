package com.sm.storagemanager.product.service.impl;

import com.sm.storagemanager.branch.entity.Branch;
import com.sm.storagemanager.product.dto.ProductDto;
import com.sm.storagemanager.product.entity.Product;
import com.sm.storagemanager.product.repository.ProductRepository;
import com.sm.storagemanager.product.service.ProductService;
import com.sm.storagemanager.productcategory.entity.ProductCategory;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AbstractCrudService<Product, ProductDto, Long> implements ProductService {

    public ProductServiceImpl(ProductRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected ProductDto toDto(Product entity) {
        return new ProductDto(
                entity.getId(),
                entity.getBranch().getId(),
                entity.getCategory() != null ? entity.getCategory().getId() : null,
                entity.getSku(),
                entity.getBarcode(),
                entity.getName(),
                entity.getDescription(),
                entity.getUnitOfMeasure(),
                entity.getCostPrice(),
                entity.getSalePrice(),
                entity.getMinStock(),
                entity.getMaxStock(),
                entity.isTracksInventory(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected Product toNewEntity(ProductDto dto) {
        Product entity = new Product();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(Product entity, ProductDto dto) {
        entity.setBranch(getReference(Branch.class, dto.getBranchId()));
        entity.setCategory(getReference(ProductCategory.class, dto.getCategoryId()));
        entity.setSku(dto.getSku());
        entity.setBarcode(dto.getBarcode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setUnitOfMeasure(dto.getUnitOfMeasure());
        entity.setCostPrice(dto.getCostPrice());
        entity.setSalePrice(dto.getSalePrice());
        entity.setMinStock(dto.getMinStock());
        entity.setMaxStock(dto.getMaxStock());
        entity.setTracksInventory(dto.isTracksInventory());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }
}

