package com.sm.storagemanager.permission.service.impl;

import com.sm.storagemanager.permission.dto.PermissionDto;
import com.sm.storagemanager.permission.entity.Permission;
import com.sm.storagemanager.permission.repository.PermissionRepository;
import com.sm.storagemanager.permission.service.PermissionService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends AbstractCrudService<Permission, PermissionDto, Long>
        implements PermissionService {

    public PermissionServiceImpl(PermissionRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected PermissionDto toDto(Permission entity) {
        return new PermissionDto(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getDescription(),
                entity.getModule(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }

    @Override
    protected Permission toNewEntity(PermissionDto dto) {
        Permission entity = new Permission();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(Permission entity, PermissionDto dto) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setModule(dto.getModule());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
    }
}

