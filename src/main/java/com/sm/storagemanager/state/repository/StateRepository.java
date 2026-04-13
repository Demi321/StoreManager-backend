package com.sm.storagemanager.state.repository;

import com.sm.storagemanager.state.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
