package com.sm.storagemanager.inventorymovement.service.impl;

import com.sm.storagemanager.appuser.entity.AppUser;
import com.sm.storagemanager.branch.entity.Branch;
import com.sm.storagemanager.inventorymovement.dto.InventoryMovementDto;
import com.sm.storagemanager.inventorymovement.entity.InventoryMovement;
import com.sm.storagemanager.inventorymovement.repository.InventoryMovementRepository;
import com.sm.storagemanager.inventorymovement.service.InventoryMovementService;
import com.sm.storagemanager.product.entity.Product;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import com.sm.storagemanager.warehouse.entity.Warehouse;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class InventoryMovementServiceImpl
        extends AbstractCrudService<InventoryMovement, InventoryMovementDto, Long>
        implements InventoryMovementService {

    public InventoryMovementServiceImpl(InventoryMovementRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected InventoryMovementDto toDto(InventoryMovement entity) {
        return new InventoryMovementDto(
                entity.getId(),
                entity.getBranch().getId(),
                entity.getWarehouse().getId(),
                entity.getProduct().getId(),
                entity.getUser().getId(),
                entity.getMovementType(),
                entity.getQuantity(),
                entity.getStockBefore(),
                entity.getStockAfter(),
                entity.getUnitCost(),
                entity.getReferenceType(),
                entity.getReferenceId(),
                entity.getNotes(),
                entity.getCreatedAt()
        );
    }

    @Override
    protected InventoryMovement toNewEntity(InventoryMovementDto dto) {
        InventoryMovement entity = new InventoryMovement();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(InventoryMovement entity, InventoryMovementDto dto) {
        entity.setBranch(getReference(Branch.class, dto.getBranchId()));
        entity.setWarehouse(getReference(Warehouse.class, dto.getWarehouseId()));
        entity.setProduct(getReference(Product.class, dto.getProductId()));
        entity.setUser(getReference(AppUser.class, dto.getUserId()));
        entity.setMovementType(dto.getMovementType());
        entity.setQuantity(dto.getQuantity());
        entity.setStockBefore(dto.getStockBefore());
        entity.setStockAfter(dto.getStockAfter());
        entity.setUnitCost(dto.getUnitCost());
        entity.setReferenceType(dto.getReferenceType());
        entity.setReferenceId(dto.getReferenceId());
        entity.setNotes(dto.getNotes());
        entity.setCreatedAt(dto.getCreatedAt());
    }
}

