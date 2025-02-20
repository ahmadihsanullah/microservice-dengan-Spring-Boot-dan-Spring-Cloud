package com.kelaskoding.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelaskoding.Entity.Product;
import com.kelaskoding.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product save(Product product){
        return productRepo.save(product);
    }

    public Optional<Product> findById(Long id){
        return productRepo.findById(id);
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }
}
