package com.sm.storagemanager.product.service.impl;

import com.sm.storagemanager.businessentity.entity.BusinessEntity;
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
                entity.getEntity().getId(),
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
        entity.setEntity(getReference(BusinessEntity.class, dto.entityId()));
        entity.setCategory(getReference(ProductCategory.class, dto.categoryId()));
        entity.setSku(dto.sku());
        entity.setBarcode(dto.barcode());
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setUnitOfMeasure(dto.unitOfMeasure());
        entity.setCostPrice(dto.costPrice());
        entity.setSalePrice(dto.salePrice());
        entity.setMinStock(dto.minStock());
        entity.setMaxStock(dto.maxStock());
        entity.setTracksInventory(dto.tracksInventory());
        entity.setStatus(dto.status());
        entity.setCreatedAt(dto.createdAt());
        entity.setUpdatedAt(dto.updatedAt());
    }
}
