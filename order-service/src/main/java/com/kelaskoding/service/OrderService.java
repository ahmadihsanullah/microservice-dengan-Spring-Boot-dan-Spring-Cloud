package com.kelaskoding.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kelaskoding.dto.Customer;
import com.kelaskoding.dto.OrderResponse;
import com.kelaskoding.entity.Order;
import com.kelaskoding.entity.OrderLine;
import com.kelaskoding.repository.OrderRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private RestTemplate restTemplate;

    private Order save(Order order){
        for(OrderLine orderLine : order.getOrderLines()){
            orderLine.setOrder(order);
        }
        return orderRepo.save(order);
    }

    public OrderResponse findById(Long id){
        Optional<Order> optOrder = orderRepo.findById(id);
        if(!optOrder.isPresent()){
            return null;
        }

        Order order = optOrder.get();
        OrderResponse orderResponse = new OrderResponse(
            order.getId(), order.getOrderNumber(), order.getOrderDate(), findCustomerById(order.getCustomerId())
        );
        return orderResponse;
    }

    public Customer findCustomerById(Long id){
        return restTemplate.getForObject("http://localhost:8081/api/customers/"+id, Customer.class);
    }
}
