package com.kelaskoding.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kelaskoding.dto.Customer;
import com.kelaskoding.dto.OrderLineResponse;
import com.kelaskoding.dto.OrderResponse;
import com.kelaskoding.dto.Product;
import com.kelaskoding.entity.Order;
import com.kelaskoding.entity.OrderLine;
import com.kelaskoding.repository.OrderRepo;
import com.kelaskoding.webclient.CustomerClient;
import com.kelaskoding.webclient.ProductClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    // @Autowired
    // private RestTemplate restTemplate;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;

    public Order save(Order order) {
        for (OrderLine orderLine : order.getOrderLines()) {
            orderLine.setOrder(order);
        }
        return orderRepo.save(order);
    }

    public OrderResponse findByOrderNumber(String orderNumber) {
        Order order = orderRepo.findByOrderNumber(orderNumber);
        if (order == null) {
            return null;
        }

        OrderResponse response = new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getOrderDate(),
                customerClient.findById(order.getCustomerId()),
                new ArrayList<OrderLineResponse>());

        for (OrderLine orderline : order.getOrderLines()) {
            Product product = productClient.findById(orderline.getProductId());
            response.getOrderlines().add(new OrderLineResponse(orderline.getId(),product, orderline.getQuantity(), orderline.getPrice()));
        }
        return response;
    }

    public OrderResponse findById(Long id) {
        Optional<Order> optOrder = orderRepo.findById(id);
        if (!optOrder.isPresent()) {
            return null;
        }

        Order order = optOrder.get();
        OrderResponse response = new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getOrderDate(),
                customerClient.findById(order.getCustomerId()),
                new ArrayList<OrderLineResponse>());

        for (OrderLine orderline : order.getOrderLines()) {
            Product product = productClient.findById(orderline.getProductId());
            response.getOrderlines().add(new OrderLineResponse(orderline.getId(),product, orderline.getQuantity(), orderline.getPrice()));
        }
        return response;
    }

    // public Customer findCustomerById(Long id) {
    //     return restTemplate.getForObject("http://CUSTOMER-SERVICE/api/customers/" + id, Customer.class);
    // }

    // public Product findProductById(Long id) {
    //     return restTemplate.getForObject("http://PRODUCT-SERVICE/api/products/" + id, Product.class);
    // }
}
