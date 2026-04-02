package com.sm.storagemanager.product.repository;

import com.sm.storagemanager.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
