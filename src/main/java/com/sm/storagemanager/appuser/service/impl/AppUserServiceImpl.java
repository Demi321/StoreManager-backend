package com.sm.storagemanager.appuser.service.impl;

import com.sm.storagemanager.approle.entity.AppRole;
import com.sm.storagemanager.appuser.dto.AppUserDto;
import com.sm.storagemanager.appuser.entity.AppUser;
import com.sm.storagemanager.appuser.repository.AppUserRepository;
import com.sm.storagemanager.appuser.service.AppUserService;
import com.sm.storagemanager.businessentity.entity.BusinessEntity;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl extends AbstractCrudService<AppUser, AppUserDto, Long> implements AppUserService {

    public AppUserServiceImpl(AppUserRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected AppUserDto toDto(AppUser entity) {
        return new AppUserDto(
                entity.getId(),
                entity.getEntity().getId(),
                entity.getRole().getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getMiddleName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getPhone(),
                entity.getStatus(),
                entity.getLastLoginAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected AppUser toNewEntity(AppUserDto dto) {
        AppUser entity = new AppUser();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(AppUser entity, AppUserDto dto) {
        entity.setEntity(getReference(BusinessEntity.class, dto.entityId()));
        entity.setRole(getReference(AppRole.class, dto.roleId()));
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setMiddleName(dto.middleName());
        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        entity.setPasswordHash(dto.passwordHash());
        entity.setPhone(dto.phone());
        entity.setStatus(dto.status());
        entity.setLastLoginAt(dto.lastLoginAt());
        entity.setCreatedAt(dto.createdAt());
        entity.setUpdatedAt(dto.updatedAt());
    }
}
