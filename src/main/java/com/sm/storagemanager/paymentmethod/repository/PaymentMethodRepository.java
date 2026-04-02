package com.sm.storagemanager.paymentmethod.repository;

import com.sm.storagemanager.paymentmethod.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
