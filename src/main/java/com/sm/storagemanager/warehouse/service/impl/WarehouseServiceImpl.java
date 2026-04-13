package com.sm.storagemanager.warehouse.service.impl;

import com.sm.storagemanager.branch.entity.Branch;
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
                entity.getBranch().getId(),
                entity.getName(),
                entity.getType(),
                entity.getPhone(),
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
        entity.setBranch(getReference(Branch.class, dto.getBranchId()));
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setPhone(dto.getPhone());
        entity.setActive(dto.isActive());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }
}

