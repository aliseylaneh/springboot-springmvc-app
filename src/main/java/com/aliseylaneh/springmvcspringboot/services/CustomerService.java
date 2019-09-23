package com.aliseylaneh.springmvcspringboot.services;

import com.aliseylaneh.springmvcspringboot.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listAllCustomer();

    Customer getCustomerById(int id);

    void saveOrUpdateCustomer(Customer customer);

    void deleteCustomer(int id);
}
