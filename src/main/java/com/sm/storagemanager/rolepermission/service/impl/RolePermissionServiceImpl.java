package com.sm.storagemanager.rolepermission.service.impl;

import com.sm.storagemanager.approle.entity.AppRole;
import com.sm.storagemanager.permission.entity.Permission;
import com.sm.storagemanager.rolepermission.dto.RolePermissionDto;
import com.sm.storagemanager.rolepermission.entity.RolePermission;
import com.sm.storagemanager.rolepermission.entity.RolePermissionId;
import com.sm.storagemanager.rolepermission.repository.RolePermissionRepository;
import com.sm.storagemanager.rolepermission.service.RolePermissionService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl
        extends AbstractCrudService<RolePermission, RolePermissionDto, RolePermissionId>
        implements RolePermissionService {

    public RolePermissionServiceImpl(RolePermissionRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected RolePermissionDto toDto(RolePermission entity) {
        return new RolePermissionDto(
                entity.getRole().getId(),
                entity.getPermission().getId(),
                entity.getCreatedAt()
        );
    }

    @Override
    protected RolePermission toNewEntity(RolePermissionDto dto) {
        RolePermission entity = new RolePermission();
        entity.setId(new RolePermissionId(dto.roleId(), dto.permissionId()));
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(RolePermission entity, RolePermissionDto dto) {
        entity.setId(new RolePermissionId(dto.roleId(), dto.permissionId()));
        entity.setRole(getReference(AppRole.class, dto.roleId()));
        entity.setPermission(getReference(Permission.class, dto.permissionId()));
        entity.setCreatedAt(dto.createdAt());
    }
}
