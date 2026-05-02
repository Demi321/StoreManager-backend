package com.sm.storagemanager.appuser.repository;

import com.sm.storagemanager.appuser.entity.AppUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsernameOrEmail(String username,String email);

    boolean existsByUsernameIgnoreCase(String username);
}
