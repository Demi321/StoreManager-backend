package com.sm.storagemanager.country.repository;

import com.sm.storagemanager.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
