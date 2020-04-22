package com.scoquix.service;

import com.scoquix.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);

    public Customer getCustomers(int customer);

    public void deleteCustomer(int customerId);

    List<Customer> searchByName(String customerName);
}
