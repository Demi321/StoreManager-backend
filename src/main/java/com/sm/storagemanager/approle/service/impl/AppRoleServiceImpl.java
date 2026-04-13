package com.sm.storagemanager.approle.service.impl;

import com.sm.storagemanager.approle.dto.AppRoleDto;
import com.sm.storagemanager.approle.entity.AppRole;
import com.sm.storagemanager.approle.repository.AppRoleRepository;
import com.sm.storagemanager.approle.service.AppRoleService;
import com.sm.storagemanager.branch.entity.Branch;
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
                entity.getBranch().getId(),
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
        entity.setBranch(getReference(Branch.class, dto.getBranchId()));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }
}

