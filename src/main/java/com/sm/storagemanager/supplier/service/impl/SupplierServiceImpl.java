package com.sm.storagemanager.supplier.service.impl;

import com.sm.storagemanager.businessentity.entity.BusinessEntity;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import com.sm.storagemanager.supplier.dto.SupplierDto;
import com.sm.storagemanager.supplier.entity.Supplier;
import com.sm.storagemanager.supplier.repository.SupplierRepository;
import com.sm.storagemanager.supplier.service.SupplierService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl extends AbstractCrudService<Supplier, SupplierDto, Long>
        implements SupplierService {

    public SupplierServiceImpl(SupplierRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected SupplierDto toDto(Supplier entity) {
        return new SupplierDto(
                entity.getId(),
                entity.getEntity().getId(),
                entity.getName(),
                entity.getTaxId(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getPostalCode(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected Supplier toNewEntity(SupplierDto dto) {
        Supplier entity = new Supplier();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(Supplier entity, SupplierDto dto) {
        entity.setEntity(getReference(BusinessEntity.class, dto.entityId()));
        entity.setName(dto.name());
        entity.setTaxId(dto.taxId());
        entity.setPhone(dto.phone());
        entity.setEmail(dto.email());
        entity.setAddressLine1(dto.addressLine1());
        entity.setAddressLine2(dto.addressLine2());
        entity.setCity(dto.city());
        entity.setState(dto.state());
        entity.setCountry(dto.country());
        entity.setPostalCode(dto.postalCode());
        entity.setStatus(dto.status());
        entity.setCreatedAt(dto.createdAt());
        entity.setUpdatedAt(dto.updatedAt());
    }
}
