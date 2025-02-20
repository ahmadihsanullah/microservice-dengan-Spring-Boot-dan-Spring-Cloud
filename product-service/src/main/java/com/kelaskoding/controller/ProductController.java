package com.kelaskoding.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelaskoding.Entity.Product;
import com.kelaskoding.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    @RequestMapping("/{id}")
    public Optional<Product> findById(@PathVariable("id") Long id){
        return productService.findById(id);
    }   

    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }
}
