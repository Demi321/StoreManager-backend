package com.sm.storagemanager.approle.repository;

import com.sm.storagemanager.approle.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
}
