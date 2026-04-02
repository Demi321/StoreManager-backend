package com.sm.storagemanager.purchaseitem.service.impl;

import com.sm.storagemanager.product.entity.Product;
import com.sm.storagemanager.purchase.entity.Purchase;
import com.sm.storagemanager.purchaseitem.dto.PurchaseItemDto;
import com.sm.storagemanager.purchaseitem.entity.PurchaseItem;
import com.sm.storagemanager.purchaseitem.repository.PurchaseItemRepository;
import com.sm.storagemanager.purchaseitem.service.PurchaseItemService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class PurchaseItemServiceImpl
        extends AbstractCrudService<PurchaseItem, PurchaseItemDto, Long>
        implements PurchaseItemService {

    public PurchaseItemServiceImpl(PurchaseItemRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected PurchaseItemDto toDto(PurchaseItem entity) {
        return new PurchaseItemDto(
                entity.getId(),
                entity.getPurchase().getId(),
                entity.getProduct().getId(),
                entity.getQuantity(),
                entity.getUnitCost(),
                entity.getLineSubtotal(),
                entity.getLineTotal()
        );
    }

    @Override
    protected PurchaseItem toNewEntity(PurchaseItemDto dto) {
        PurchaseItem entity = new PurchaseItem();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(PurchaseItem entity, PurchaseItemDto dto) {
        entity.setPurchase(getReference(Purchase.class, dto.purchaseId()));
        entity.setProduct(getReference(Product.class, dto.productId()));
        entity.setQuantity(dto.quantity());
        entity.setUnitCost(dto.unitCost());
        entity.setLineSubtotal(dto.lineSubtotal());
        entity.setLineTotal(dto.lineTotal());
    }
}
