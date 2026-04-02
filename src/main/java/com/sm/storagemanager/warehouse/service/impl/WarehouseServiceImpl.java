package com.sm.storagemanager.warehouse.service.impl;

import com.sm.storagemanager.businessentity.entity.BusinessEntity;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import com.sm.storagemanager.warehouse.dto.WarehouseDto;
import com.sm.storagemanager.warehouse.entity.Warehouse;
import com.sm.storagemanager.warehouse.repository.WarehouseRepository;
import com.sm.storagemanager.warehouse.service.WarehouseService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl extends AbstractCrudService<Warehouse, WarehouseDto, Long>
        implements WarehouseService {

    public WarehouseServiceImpl(WarehouseRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected WarehouseDto toDto(Warehouse entity) {
        return new WarehouseDto(
                entity.getId(),
                entity.getEntity().getId(),
                entity.getName(),
                entity.getType(),
                entity.getPhone(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getPostalCode(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected Warehouse toNewEntity(WarehouseDto dto) {
        Warehouse entity = new Warehouse();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(Warehouse entity, WarehouseDto dto) {
        entity.setEntity(getReference(BusinessEntity.class, dto.entityId()));
        entity.setName(dto.name());
        entity.setType(dto.type());
        entity.setPhone(dto.phone());
        entity.setAddressLine1(dto.addressLine1());
        entity.setAddressLine2(dto.addressLine2());
        entity.setCity(dto.city());
        entity.setState(dto.state());
        entity.setCountry(dto.country());
        entity.setPostalCode(dto.postalCode());
        entity.setActive(dto.active());
        entity.setCreatedAt(dto.createdAt());
        entity.setUpdatedAt(dto.updatedAt());
    }
}
