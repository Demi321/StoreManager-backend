package com.sm.storagemanager.customer.service.impl;

import com.sm.storagemanager.businessentity.entity.BusinessEntity;
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
                entity.getEntity().getId(),
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
        entity.setEntity(getReference(BusinessEntity.class, dto.entityId()));
        entity.setName(dto.name());
        entity.setCustomerType(dto.customerType());
        entity.setTaxId(dto.taxId());
        entity.setPhone(dto.phone());
        entity.setEmail(dto.email());
        entity.setAddressLine1(dto.addressLine1());
        entity.setAddressLine2(dto.addressLine2());
        entity.setCity(dto.city());
        entity.setState(dto.state());
        entity.setCountry(dto.country());
        entity.setPostalCode(dto.postalCode());
        entity.setActive(dto.active());
        entity.setCreatedAt(dto.createdAt());
        entity.setUpdatedAt(dto.updatedAt());
    }
}
