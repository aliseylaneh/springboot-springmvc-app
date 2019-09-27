package com.aliseylaneh.springmvcspringboot.services;

import com.aliseylaneh.springmvcspringboot.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CustomerService {
    List<Customer> listAllCustomers();

    Customer getCustomerById(int id);

    Customer saveOrUpdateCustomer(Customer customer);

    void deleteCustomer(int id);
}
