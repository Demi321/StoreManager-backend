package com.sm.storagemanager.sale.service.impl;

import com.sm.storagemanager.appuser.entity.AppUser;
import com.sm.storagemanager.branch.entity.Branch;
import com.sm.storagemanager.customer.entity.Customer;
import com.sm.storagemanager.sale.dto.SaleDto;
import com.sm.storagemanager.sale.entity.Sale;
import com.sm.storagemanager.sale.repository.SaleRepository;
import com.sm.storagemanager.sale.service.SaleService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import com.sm.storagemanager.warehouse.entity.Warehouse;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl extends AbstractCrudService<Sale, SaleDto, Long> implements SaleService {

    public SaleServiceImpl(SaleRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected SaleDto toDto(Sale entity) {
        return new SaleDto(
                entity.getId(),
                entity.getBranch().getId(),
                entity.getWarehouse().getId(),
                entity.getUser().getId(),
                entity.getCustomer() != null ? entity.getCustomer().getId() : null,
                entity.getSaleFolio(),
                entity.getSaleDate(),
                entity.getSubtotal(),
                entity.getDiscountTotal(),
                entity.getTaxTotal(),
                entity.getTotal(),
                entity.getStatus(),
                entity.getSaleType(),
                entity.getNotes(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    protected Sale toNewEntity(SaleDto dto) {
        Sale entity = new Sale();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(Sale entity, SaleDto dto) {
        entity.setBranch(getReference(Branch.class, dto.getBranchId()));
        entity.setWarehouse(getReference(Warehouse.class, dto.getWarehouseId()));
        entity.setUser(getReference(AppUser.class, dto.getUserId()));
        entity.setCustomer(getReference(Customer.class, dto.getCustomerId()));
        entity.setSaleFolio(dto.getSaleFolio());
        entity.setSaleDate(dto.getSaleDate());
        entity.setSubtotal(dto.getSubtotal());
        entity.setDiscountTotal(dto.getDiscountTotal());
        entity.setTaxTotal(dto.getTaxTotal());
        entity.setTotal(dto.getTotal());
        entity.setStatus(dto.getStatus());
        entity.setSaleType(dto.getSaleType());
        entity.setNotes(dto.getNotes());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
    }
}

