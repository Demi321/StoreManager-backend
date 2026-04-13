package com.sm.storagemanager.customer.service.impl;

import com.sm.storagemanager.branch.entity.Branch;
import com.sm.storagemanager.customer.dto.CustomerDto;
import com.sm.storagemanager.customer.entity.Customer;
import com.sm.storagemanager.customer.repository.CustomerRepository;
import com.sm.storagemanager.customer.service.CustomerService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends AbstractCrudService<Customer, CustomerDto, Long>
        implements CustomerService {

    public CustomerServiceImpl(CustomerRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected CustomerDto toDto(Customer entity) {
        return new CustomerDto(
                entity.getId(),
                entity.getBranch().getId(),
                entity.getName(),
                entity.getCustomerType(),
                entity.getTaxId(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getPostalCode(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected Customer toNewEntity(CustomerDto dto) {
        Customer entity = new Customer();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(Customer entity, CustomerDto dto) {
        entity.setBranch(getReference(Branch.class, dto.getBranchId()));
        entity.setName(dto.getName());
        entity.setCustomerType(dto.getCustomerType());
        entity.setTaxId(dto.getTaxId());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setAddressLine1(dto.getAddressLine1());
        entity.setAddressLine2(dto.getAddressLine2());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setCountry(dto.getCountry());
        entity.setPostalCode(dto.getPostalCode());
        entity.setActive(dto.isActive());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }
}

