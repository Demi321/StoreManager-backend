package com.sm.storagemanager.salepayment.repository;

import com.sm.storagemanager.salepayment.entity.SalePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalePaymentRepository extends JpaRepository<SalePayment, Long> {
}
