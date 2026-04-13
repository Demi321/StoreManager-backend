package com.sm.storagemanager.purchase.service.impl;

import com.sm.storagemanager.appuser.entity.AppUser;
import com.sm.storagemanager.branch.entity.Branch;
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
                entity.getBranch().getId(),
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
        entity.setBranch(getReference(Branch.class, dto.getBranchId()));
        entity.setSupplier(getReference(Supplier.class, dto.getSupplierId()));
        entity.setWarehouse(getReference(Warehouse.class, dto.getWarehouseId()));
        entity.setUser(getReference(AppUser.class, dto.getUserId()));
        entity.setPurchaseFolio(dto.getPurchaseFolio());
        entity.setPurchaseDate(dto.getPurchaseDate());
        entity.setSubtotal(dto.getSubtotal());
        entity.setTaxTotal(dto.getTaxTotal());
        entity.setTotal(dto.getTotal());
        entity.setStatus(dto.getStatus());
        entity.setNotes(dto.getNotes());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }
}

