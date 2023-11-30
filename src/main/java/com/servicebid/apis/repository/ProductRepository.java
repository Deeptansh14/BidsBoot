package com.servicebid.apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicebid.apis.models.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

    
}
