package com.sm.storagemanager.sector.repository;

import com.sm.storagemanager.sector.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Long> {
}
