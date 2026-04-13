package com.sm.storagemanager.appuser.service.impl;

import com.sm.storagemanager.approle.entity.AppRole;
import com.sm.storagemanager.appuser.dto.AppUserDto;
import com.sm.storagemanager.appuser.entity.AppUser;
import com.sm.storagemanager.appuser.repository.AppUserRepository;
import com.sm.storagemanager.appuser.service.AppUserService;
import com.sm.storagemanager.branch.entity.Branch;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl extends AbstractCrudService<AppUser, AppUserDto, Long> implements AppUserService {

    private final AppUserRepository repository;

    public AppUserServiceImpl(AppUserRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
        this.repository = repository;
    }

    @Override
    protected AppUserDto toDto(AppUser entity) {
        return new AppUserDto(
                entity.getId(),
                entity.getBranch().getId(),
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
        entity.setBranch(getReference(Branch.class, dto.getBranchId()));
        entity.setRole(getReference(AppRole.class, dto.getRoleId()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPasswordHash(dto.getPassword());
        entity.setPhone(dto.getPhone());
        entity.setStatus(dto.getStatus());
        entity.setLastLoginAt(dto.getLastLoginAt());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }

    @Override
    public boolean existsByUserName(String userName) {
        return repository.existsByUsernameIgnoreCase(userName);
    }
}

