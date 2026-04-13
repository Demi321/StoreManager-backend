package com.sm.storagemanager.branchaddress.service.impl;

import com.sm.storagemanager.branch.entity.Branch;
import com.sm.storagemanager.branchaddress.dto.BranchAddressDto;
import com.sm.storagemanager.branchaddress.entity.BranchAddress;
import com.sm.storagemanager.branchaddress.repository.BranchAddressRepository;
import com.sm.storagemanager.branchaddress.service.BranchAddressService;
import com.sm.storagemanager.country.entity.Country;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import com.sm.storagemanager.state.entity.State;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class BranchAddressServiceImpl extends AbstractCrudService<BranchAddress, BranchAddressDto, Long>
        implements BranchAddressService {

    public BranchAddressServiceImpl(BranchAddressRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected BranchAddressDto toDto(BranchAddress entity) {
        return new BranchAddressDto(
                entity.getId(),
                entity.getBranch().getId(),
                entity.getCountry().getId(),
                entity.getState().getId(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getCity(),
                entity.getPostalCode(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected BranchAddress toNewEntity(BranchAddressDto dto) {
        BranchAddress entity = new BranchAddress();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(BranchAddress entity, BranchAddressDto dto) {
        entity.setBranch(getReference(Branch.class, dto.getBranchId()));
        entity.setCountry(getReference(Country.class, dto.getCountryId()));
        entity.setState(getReference(State.class, dto.getStateId()));
        entity.setAddressLine1(dto.getAddressLine1());
        entity.setAddressLine2(dto.getAddressLine2());
        entity.setCity(dto.getCity());
        entity.setPostalCode(dto.getPostalCode());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }
}

