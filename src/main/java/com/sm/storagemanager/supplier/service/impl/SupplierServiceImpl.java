package com.sm.storagemanager.supplier.service.impl;

import com.sm.storagemanager.branch.entity.Branch;
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
                entity.getBranch().getId(),
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
        entity.setBranch(getReference(Branch.class, dto.getBranchId()));
        entity.setName(dto.getName());
        entity.setTaxId(dto.getTaxId());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setAddressLine1(dto.getAddressLine1());
        entity.setAddressLine2(dto.getAddressLine2());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setCountry(dto.getCountry());
        entity.setPostalCode(dto.getPostalCode());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }
}

