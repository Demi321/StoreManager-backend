package com.sm.storagemanager.sector.service.impl;

import com.sm.storagemanager.sector.dto.SectorDto;
import com.sm.storagemanager.sector.entity.Sector;
import com.sm.storagemanager.sector.repository.SectorRepository;
import com.sm.storagemanager.sector.service.SectorService;
import com.sm.storagemanager.shared.crud.service.impl.AbstractCrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class SectorServiceImpl extends AbstractCrudService<Sector, SectorDto, Long> implements SectorService {

    public SectorServiceImpl(SectorRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected SectorDto toDto(Sector entity) {
        return new SectorDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreatedAt()
        );
    }

    @Override
    protected Sector toNewEntity(SectorDto dto) {
        Sector entity = new Sector();
        updateEntity(entity, dto);
        return entity;
    }

    @Override
    protected void updateEntity(Sector entity, SectorDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCreatedAt(dto.getCreatedAt());
    }
}

