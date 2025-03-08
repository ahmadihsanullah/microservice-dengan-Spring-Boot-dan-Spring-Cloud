package com.kelaskoding.dto;

import java.util.Date;
import java.util.List;

public class OrderResponse {

    private Long id;
    private String orderNumber;
    private Date orderDate;
    private Customer customer;
    private List<OrderLineResponse> orderlines;

    public List<OrderLineResponse> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<OrderLineResponse> orderlines) {
        this.orderlines = orderlines;
    }

    public OrderResponse() {
    }

    public OrderResponse(Long id, String orderNumber, Date orderDate, Customer customer, List<OrderLineResponse> orderlines) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.customer = customer;
        this.orderlines = orderlines;
    }

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
