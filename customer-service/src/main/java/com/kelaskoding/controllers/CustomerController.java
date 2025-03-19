package com.kelaskoding.controllers;

import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelaskoding.dto.ResponseData;
import com.kelaskoding.dto.SearchEmailRequest;
import com.kelaskoding.entity.Customer;
import com.kelaskoding.services.CustomerService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<ResponseData<Customer>> save(@Valid @RequestBody Customer customer, Errors errors){
        ResponseData<Customer> response = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error: errors.getAllErrors()){
                response.getMessage().add(error.getDefaultMessage());
            }
            response.setStatus(false);
            response.setPayload(null);
            return ResponseEntity.badRequest().body(response);
        }
        try {
            customerService.save(customer);
            response.setStatus(true);
            response.setMessage(List.of("Customer saved successfully"));
            response.setPayload(customerService.save(customer));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setMessage(Arrays.asList(e.getMessage()));
            response.setMessage(List.of(e.getMessage()));
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public Iterable<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer customerFindById(@PathVariable("id") Long id){
        return customerService.findById(id);
    }

    @GetMapping("/search-by-email")
    public Customer customerFindByEmail(@RequestBody SearchEmailRequest form){
        return customerService.findByEmail(form.getEmail());
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void delete(@PathVariable("id") Long id){
        customerService.delete(id);
    }

}
