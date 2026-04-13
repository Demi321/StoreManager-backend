package com.sm.storagemanager.businessentity.service.impl;

import com.sm.storagemanager.businessentity.dto.BusinessEntityDto;
import com.sm.storagemanager.businessentity.entity.BusinessEntity;
import com.sm.storagemanager.businessentity.exception.BusinessEntityAlreadyExistsException;
import com.sm.storagemanager.businessentity.repository.BusinessEntityRepository;
import com.sm.storagemanager.businessentity.service.BusinessEntityService;
import com.sm.storagemanager.sector.entity.Sector;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class BusinessEntityServiceImpl extends AbstractCrudService<BusinessEntity, BusinessEntityDto, Long>
        implements BusinessEntityService {

    private final BusinessEntityRepository repository;

    public BusinessEntityServiceImpl(BusinessEntityRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
        this.repository = repository;
    }

    @Override
    @Transactional
    public BusinessEntityDto create(BusinessEntityDto dto) {

        try {
            
            String entityName = dto.getName();

            if (entityName != null && repository.existsByName(entityName)) {
                throw new BusinessEntityAlreadyExistsException(entityName);
            }

            BusinessEntity entity = toNewEntity(dto);

            return toDto(repository.save(entity));

        } catch (DataIntegrityViolationException ex) {
            String rootCauseMessage = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
            log.warn(
                    "Data integrity violation while creating business entity. name={}, taxId={}, cause={}",
                    dto.getName(),
                    dto.getTaxId(),
                    rootCauseMessage);

            throw ex;
        }
    }

    @Override
    protected BusinessEntityDto toDto(BusinessEntity entity) {
        return new BusinessEntityDto(
                entity.getId(),
                entity.getSector().getId(),
                entity.getName(),
                entity.getLegalName(),
                entity.getTaxId(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    @Override
    protected BusinessEntity toNewEntity(BusinessEntityDto dto) {
        BusinessEntity entity = new BusinessEntity();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(BusinessEntity entity, BusinessEntityDto dto) {
        entity.setSector(getReference(Sector.class, dto.getSectorId()));
        entity.setName(dto.getName());
        entity.setLegalName(dto.getLegalName());
        entity.setTaxId(dto.getTaxId());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

}

