package com.product.eurukaclient.repository;

import com.product.eurukaclient.enity.ProductLookUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookUpRepository extends JpaRepository<ProductLookUpEntity,String> {
    ProductLookUpEntity findByProductIdOrTitle(String product,String title);
}
