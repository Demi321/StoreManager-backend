package com.sm.storagemanager.productcategory.repository;

import com.sm.storagemanager.productcategory.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
