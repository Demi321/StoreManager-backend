package com.sm.storagemanager.customer.repository;

import com.sm.storagemanager.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
