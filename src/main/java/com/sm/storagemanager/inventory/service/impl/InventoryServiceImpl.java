package com.sm.storagemanager.inventory.service.impl;

import com.sm.storagemanager.businessentity.entity.BusinessEntity;
import com.sm.storagemanager.inventory.dto.InventoryDto;
import com.sm.storagemanager.inventory.entity.Inventory;
import com.sm.storagemanager.inventory.repository.InventoryRepository;
import com.sm.storagemanager.inventory.service.InventoryService;
import com.sm.storagemanager.product.entity.Product;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import com.sm.storagemanager.warehouse.entity.Warehouse;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl extends AbstractCrudService<Inventory, InventoryDto, Long>
        implements InventoryService {

    public InventoryServiceImpl(InventoryRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected InventoryDto toDto(Inventory entity) {
        return new InventoryDto(
                entity.getId(),
                entity.getEntity().getId(),
                entity.getWarehouse().getId(),
                entity.getProduct().getId(),
                entity.getStockOnHand(),
                entity.getStockReserved(),
                entity.getStockAvailable(),
                entity.getAverageCost(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected Inventory toNewEntity(InventoryDto dto) {
        Inventory entity = new Inventory();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(Inventory entity, InventoryDto dto) {
        entity.setEntity(getReference(BusinessEntity.class, dto.entityId()));
        entity.setWarehouse(getReference(Warehouse.class, dto.warehouseId()));
        entity.setProduct(getReference(Product.class, dto.productId()));
        entity.setStockOnHand(dto.stockOnHand());
        entity.setStockReserved(dto.stockReserved());
        entity.setAverageCost(dto.averageCost());
        entity.setUpdatedAt(dto.updatedAt());
    }
}
