package com.sm.storagemanager.purchase.service.impl;

import com.sm.storagemanager.appuser.entity.AppUser;
import com.sm.storagemanager.businessentity.entity.BusinessEntity;
import com.sm.storagemanager.purchase.dto.PurchaseDto;
import com.sm.storagemanager.purchase.entity.Purchase;
import com.sm.storagemanager.purchase.repository.PurchaseRepository;
import com.sm.storagemanager.purchase.service.PurchaseService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import com.sm.storagemanager.supplier.entity.Supplier;
import com.sm.storagemanager.warehouse.entity.Warehouse;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl extends AbstractCrudService<Purchase, PurchaseDto, Long>
        implements PurchaseService {

    public PurchaseServiceImpl(PurchaseRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected PurchaseDto toDto(Purchase entity) {
        return new PurchaseDto(
                entity.getId(),
                entity.getEntity().getId(),
                entity.getSupplier().getId(),
                entity.getWarehouse().getId(),
                entity.getUser().getId(),
                entity.getPurchaseFolio(),
                entity.getPurchaseDate(),
                entity.getSubtotal(),
                entity.getTaxTotal(),
                entity.getTotal(),
                entity.getStatus(),
                entity.getNotes(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected Purchase toNewEntity(PurchaseDto dto) {
        Purchase entity = new Purchase();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(Purchase entity, PurchaseDto dto) {
        entity.setEntity(getReference(BusinessEntity.class, dto.entityId()));
        entity.setSupplier(getReference(Supplier.class, dto.supplierId()));
        entity.setWarehouse(getReference(Warehouse.class, dto.warehouseId()));
        entity.setUser(getReference(AppUser.class, dto.userId()));
        entity.setPurchaseFolio(dto.purchaseFolio());
        entity.setPurchaseDate(dto.purchaseDate());
        entity.setSubtotal(dto.subtotal());
        entity.setTaxTotal(dto.taxTotal());
        entity.setTotal(dto.total());
        entity.setStatus(dto.status());
        entity.setNotes(dto.notes());
        entity.setCreatedAt(dto.createdAt());
        entity.setUpdatedAt(dto.updatedAt());
    }
}
