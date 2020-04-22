package com.scoquix.dao;

import com.scoquix.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getCustomers();
    public void saveCustomer(Customer customer);

    public Customer getCustomer(int customer);

    public void deleteCustomer(int customerId);

    public List<Customer> searchByName(String customerName);
}
