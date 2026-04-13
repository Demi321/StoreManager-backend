package com.sm.storagemanager.state.service.impl;

import com.sm.storagemanager.country.entity.Country;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import com.sm.storagemanager.state.dto.StateDto;
import com.sm.storagemanager.state.entity.State;
import com.sm.storagemanager.state.repository.StateRepository;
import com.sm.storagemanager.state.service.StateService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl extends AbstractCrudService<State, StateDto, Long> implements StateService {

    public StateServiceImpl(StateRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected StateDto toDto(State entity) {
        return new StateDto(
                entity.getId(),
                entity.getCountry().getId(),
                entity.getCode(),
                entity.getName(),
                entity.getCreatedAt()
        );
    }

    @Override
    protected State toNewEntity(StateDto dto) {
        State entity = new State();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(State entity, StateDto dto) {
        entity.setCountry(getReference(Country.class, dto.getCountryId()));
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setCreatedAt(dto.getCreatedAt());
    }
}

