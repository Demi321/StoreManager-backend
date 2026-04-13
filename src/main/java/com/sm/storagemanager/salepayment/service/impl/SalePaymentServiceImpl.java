package com.sm.storagemanager.salepayment.service.impl;

import com.sm.storagemanager.paymentmethod.entity.PaymentMethod;
import com.sm.storagemanager.sale.entity.Sale;
import com.sm.storagemanager.salepayment.dto.SalePaymentDto;
import com.sm.storagemanager.salepayment.entity.SalePayment;
import com.sm.storagemanager.salepayment.repository.SalePaymentRepository;
import com.sm.storagemanager.salepayment.service.SalePaymentService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class SalePaymentServiceImpl
        extends AbstractCrudService<SalePayment, SalePaymentDto, Long>
        implements SalePaymentService {

    public SalePaymentServiceImpl(SalePaymentRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected SalePaymentDto toDto(SalePayment entity) {
        return new SalePaymentDto(
                entity.getId(),
                entity.getSale().getId(),
                entity.getPaymentMethod().getId(),
                entity.getAmount(),
                entity.getPaymentReference(),
                entity.getPaidAt()
        );
    }

    @Override
    protected SalePayment toNewEntity(SalePaymentDto dto) {
        SalePayment entity = new SalePayment();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(SalePayment entity, SalePaymentDto dto) {
        entity.setSale(getReference(Sale.class, dto.getSaleId()));
        entity.setPaymentMethod(getReference(PaymentMethod.class, dto.getPaymentMethodId()));
        entity.setAmount(dto.getAmount());
        entity.setPaymentReference(dto.getPaymentReference());
        entity.setPaidAt(dto.getPaidAt());
    }
}

