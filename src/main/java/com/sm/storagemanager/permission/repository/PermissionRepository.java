package com.sm.storagemanager.permission.repository;

import com.sm.storagemanager.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
