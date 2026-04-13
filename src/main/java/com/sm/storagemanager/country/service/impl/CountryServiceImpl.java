package com.sm.storagemanager.country.service.impl;

import com.sm.storagemanager.country.dto.CountryDto;
import com.sm.storagemanager.country.entity.Country;
import com.sm.storagemanager.country.repository.CountryRepository;
import com.sm.storagemanager.country.service.CountryService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends AbstractCrudService<Country, CountryDto, Long> implements CountryService {

    public CountryServiceImpl(CountryRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected CountryDto toDto(Country entity) {
        return new CountryDto(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getCreatedAt()
        );
    }

    @Override
    protected Country toNewEntity(CountryDto dto) {
        Country entity = new Country();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(Country entity, CountryDto dto) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setCreatedAt(dto.getCreatedAt());
    }
}

