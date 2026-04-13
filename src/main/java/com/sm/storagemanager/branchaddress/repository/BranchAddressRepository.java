package com.sm.storagemanager.branchaddress.repository;

import com.sm.storagemanager.branchaddress.entity.BranchAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchAddressRepository extends JpaRepository<BranchAddress, Long> {
}
