package com.sm.storagemanager.branch.repository;

import com.sm.storagemanager.branch.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
