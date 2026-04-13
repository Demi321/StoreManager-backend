package com.sm.storagemanager.saleitem.service.impl;

import com.sm.storagemanager.product.entity.Product;
import com.sm.storagemanager.sale.entity.Sale;
import com.sm.storagemanager.saleitem.dto.SaleItemDto;
import com.sm.storagemanager.saleitem.entity.SaleItem;
import com.sm.storagemanager.saleitem.repository.SaleItemRepository;
import com.sm.storagemanager.saleitem.service.SaleItemService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class SaleItemServiceImpl extends AbstractCrudService<SaleItem, SaleItemDto, Long> implements SaleItemService {

    public SaleItemServiceImpl(SaleItemRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected SaleItemDto toDto(SaleItem entity) {
        return new SaleItemDto(
                entity.getId(),
                entity.getSale().getId(),
                entity.getProduct().getId(),
                entity.getQuantity(),
                entity.getUnitPrice(),
                entity.getUnitDiscount(),
                entity.getUnitTax(),
                entity.getLineSubtotal(),
                entity.getLineTotal()
        );
    }

    @Override
    protected SaleItem toNewEntity(SaleItemDto dto) {
        SaleItem entity = new SaleItem();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(SaleItem entity, SaleItemDto dto) {
        entity.setSale(getReference(Sale.class, dto.getSaleId()));
        entity.setProduct(getReference(Product.class, dto.getProductId()));
        entity.setQuantity(dto.getQuantity());
        entity.setUnitPrice(dto.getUnitPrice());
        entity.setUnitDiscount(dto.getUnitDiscount());
        entity.setUnitTax(dto.getUnitTax());
        entity.setLineSubtotal(dto.getLineSubtotal());
        entity.setLineTotal(dto.getLineTotal());
    }
}

