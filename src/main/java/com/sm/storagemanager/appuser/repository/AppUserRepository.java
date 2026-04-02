package com.sm.storagemanager.appuser.repository;

import com.sm.storagemanager.appuser.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
