package com.sm.storagemanager.branch.service.impl;

import com.sm.storagemanager.branch.dto.BranchDto;
import com.sm.storagemanager.branch.entity.Branch;
import com.sm.storagemanager.branch.repository.BranchRepository;
import com.sm.storagemanager.branch.service.BranchService;
import com.sm.storagemanager.businessentity.entity.BusinessEntity;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl extends AbstractCrudService<Branch, BranchDto, Long> implements BranchService {

    public BranchServiceImpl(BranchRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected BranchDto toDto(Branch entity) {
        return new BranchDto(
                entity.getId(),
                entity.getBusinessEntity().getId(),
                entity.getName(),
                entity.getCode(),
                entity.getPhone(),
                entity.getEmail(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected Branch toNewEntity(BranchDto dto) {
        Branch entity = new Branch();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(Branch entity, BranchDto dto) {
        entity.setBusinessEntity(getReference(BusinessEntity.class, dto.getBusinessEntityId()));
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setActive(dto.isActive());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }
}

