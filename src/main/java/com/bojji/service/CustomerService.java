package com.bojji.service;

import com.bojji.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers(int sort);
    void saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomers(String key);
}
