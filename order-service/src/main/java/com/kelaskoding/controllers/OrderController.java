package com.kelaskoding.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelaskoding.dto.OrderResponse;
import com.kelaskoding.entity.Order;
import com.kelaskoding.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/order-number/{number}")
    public OrderResponse findByOrderNumber(@PathVariable("number") String number){
        return orderService.findByOrderNumber(number);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "fallbackFindCustomerById")
    public OrderResponse findById(@PathVariable("id") Long id){
        return orderService.findById(id);
    }

    public OrderResponse fallbackFindCustomerById(Long id, Throwable throwable) {
        return new OrderResponse();
    }

    @PostMapping
    public Order save(@RequestBody Order order){
        return orderService.save(order);
    }
}
