package com.sm.storagemanager.inventorymovement.service.impl;

import com.sm.storagemanager.appuser.entity.AppUser;
import com.sm.storagemanager.businessentity.entity.BusinessEntity;
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
                entity.getEntity().getId(),
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
        entity.setEntity(getReference(BusinessEntity.class, dto.entityId()));
        entity.setWarehouse(getReference(Warehouse.class, dto.warehouseId()));
        entity.setProduct(getReference(Product.class, dto.productId()));
        entity.setUser(getReference(AppUser.class, dto.userId()));
        entity.setMovementType(dto.movementType());
        entity.setQuantity(dto.quantity());
        entity.setStockBefore(dto.stockBefore());
        entity.setStockAfter(dto.stockAfter());
        entity.setUnitCost(dto.unitCost());
        entity.setReferenceType(dto.referenceType());
        entity.setReferenceId(dto.referenceId());
        entity.setNotes(dto.notes());
        entity.setCreatedAt(dto.createdAt());
    }
}
