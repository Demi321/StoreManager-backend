package com.sm.storagemanager.approle.service.impl;

import com.sm.storagemanager.approle.dto.AppRoleDto;
import com.sm.storagemanager.approle.entity.AppRole;
import com.sm.storagemanager.approle.repository.AppRoleRepository;
import com.sm.storagemanager.approle.service.AppRoleService;
import com.sm.storagemanager.businessentity.entity.BusinessEntity;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class AppRoleServiceImpl extends AbstractCrudService<AppRole, AppRoleDto, Long> implements AppRoleService {

    public AppRoleServiceImpl(AppRoleRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected AppRoleDto toDto(AppRole entity) {
        return new AppRoleDto(
                entity.getId(),
                entity.getEntity().getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected AppRole toNewEntity(AppRoleDto dto) {
        AppRole entity = new AppRole();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(AppRole entity, AppRoleDto dto) {
        entity.setEntity(getReference(BusinessEntity.class, dto.entityId()));
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setStatus(dto.status());
        entity.setCreatedAt(dto.createdAt());
        entity.setUpdatedAt(dto.updatedAt());
    }
}
