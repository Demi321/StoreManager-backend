package com.sm.storagemanager.shared.crud.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<D, ID> {

    List<D> findAll();

    Optional<D> findById(ID id);

    D create(D dto);

    D update(ID id, D dto);

    void deleteById(ID id);
}
