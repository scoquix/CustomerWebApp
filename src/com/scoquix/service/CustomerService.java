package com.scoquix.service;

import com.scoquix.entity.Customer;

import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);
}
