package com.kelaskoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelaskoding.entity.Order;
import com.kelaskoding.entity.OrderLine;
import com.kelaskoding.repository.OrderRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    // ketika kita membuat order,
    // dia akan menyimpan detailnya ke order,
    // dan menyimpan detail barang ke order line
    // maka kita butuh transaction

    private Order save(Order order){
        for(OrderLine orderLine : order.getOrderLines()){
            orderLine.setOrder(order);
        }
        return orderRepo.save(order);
    }

    public Order findById(Long id){
        return orderRepo.findById(id).orElse(null);
    }


}
