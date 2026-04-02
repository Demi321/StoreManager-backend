package com.sm.storagemanager.rolepermission.repository;

import com.sm.storagemanager.rolepermission.entity.RolePermission;
import com.sm.storagemanager.rolepermission.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
}
