package com.sm.storagemanager.businessentity.repository;

import com.sm.storagemanager.businessentity.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessEntityRepository extends JpaRepository<BusinessEntity, Long> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
