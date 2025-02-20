package com.kelaskoding.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelaskoding.entity.Customer;


public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
