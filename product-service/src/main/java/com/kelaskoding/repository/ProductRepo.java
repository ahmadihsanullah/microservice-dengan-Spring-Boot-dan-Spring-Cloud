package com.kelaskoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelaskoding.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
    
    
}
