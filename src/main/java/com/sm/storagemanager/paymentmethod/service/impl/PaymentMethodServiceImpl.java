package com.sm.storagemanager.paymentmethod.service.impl;

import com.sm.storagemanager.paymentmethod.dto.PaymentMethodDto;
import com.sm.storagemanager.paymentmethod.entity.PaymentMethod;
import com.sm.storagemanager.paymentmethod.repository.PaymentMethodRepository;
import com.sm.storagemanager.paymentmethod.service.PaymentMethodService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImpl
        extends AbstractCrudService<PaymentMethod, PaymentMethodDto, Long>
        implements PaymentMethodService {

    public PaymentMethodServiceImpl(PaymentMethodRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected PaymentMethodDto toDto(PaymentMethod entity) {
        return new PaymentMethodDto(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }

    @Override
    protected PaymentMethod toNewEntity(PaymentMethodDto dto) {
        PaymentMethod entity = new PaymentMethod();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(PaymentMethod entity, PaymentMethodDto dto) {
        entity.setCode(dto.code());
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setStatus(dto.status());
        entity.setCreatedAt(dto.createdAt());
    }
}
