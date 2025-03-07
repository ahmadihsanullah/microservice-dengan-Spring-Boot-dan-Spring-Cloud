package com.kelaskoding.dto;

import java.util.Date;

public class OrderResponse {
    
    public OrderResponse() {
    }
    
    public OrderResponse(Long id, String orderNumber, Date orderDate, Customer customer) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.customer = customer;
    }

    private Long id;
    private String orderNumber;
    private Date orderDate;
    private Customer customer;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
