package com.sm.storagemanager.shared.crud.service.impl;

import com.sm.storagemanager.shared.crud.service.CrudService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class AbstractCrudService<E, D, ID> implements CrudService<D, ID> {

    private final JpaRepository<E, ID> repository;
    protected final EntityManager entityManager;

    protected AbstractCrudService(JpaRepository<E, ID> repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public List<D> findAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Optional<D> findById(ID id) {
        return repository.findById(id).map(this::toDto);
    }

    @Override
    @Transactional
    public D create(D dto) {
        E entity = toNewEntity(dto);
        return toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public List<D> createAll(List<D> dtos) {
        List<E> entities = dtos.stream()
                .map(this::toNewEntity)
                .toList();

        return repository.saveAll(entities).stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional
    public D update(ID id, D dto) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
        updateEntity(entity, dto);
        return toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    protected <T> T getReference(Class<T> entityClass, Object id) {
        return id == null ? null : entityManager.getReference(entityClass, id);
    }

    protected abstract D toDto(E entity);

    protected abstract E toNewEntity(D dto);

    protected abstract void updateEntity(E entity, D dto);
}
