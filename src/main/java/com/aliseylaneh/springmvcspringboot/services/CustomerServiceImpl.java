package com.aliseylaneh.springmvcspringboot.services;

import com.aliseylaneh.springmvcspringboot.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<Integer, Customer> customers;

    public CustomerServiceImpl() {
        loadCustomers();
    }

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomerById(int id) {
        return customers.get(id);
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        if (customer != null) {
            if (customer.getId() == null) {
                customer.setId(getNextKey());
            }
            customers.put(customer.getId(), customer);
            return customer;
        } else {
            throw new RuntimeException("Customer cant be Null");
        }
    }

    private int getNextKey() {
        return Collections.max(customers.keySet()) + 1;
    }

    @Override
    public void deleteCustomer(int id) {
        customers.remove(id);

    }

    private void loadCustomers() {
        customers = new HashMap<>();
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Alex");
        customer1.setLastName("Wick");
        customer1.setPhoneNumber("01-555-4564");
        customer1.setAddressLineOne("Alley 5 , Street 5");
        customer1.setAddressLineTwo("Alley 2, Street 5");
        customer1.setCity("California");
        customer1.setZipCode("988321");
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Ali");
        customer2.setLastName("Seylaneh");
        customer2.setPhoneNumber("01-555-4564");
        customer2.setAddressLineOne("Alley 5 , Street 5");
        customer2.setAddressLineTwo("Alley 2, Street 5");
        customer2.setCity("New York");
        customer2.setZipCode("558667");

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("John");
        customer3.setLastName("Petterson");
        customer3.setPhoneNumber("01-555-4564");
        customer3.setAddressLineOne("Alley 5 , Street 5");
        customer3.setAddressLineTwo("Alley 2, Street 5");
        customer3.setCity("Calgray");
        customer3.setZipCode("890067");
        Customer customer4 = new Customer();
        customer4.setId(4);
        customer4.setFirstName("Alita");
        customer4.setLastName("Tompson");
        customer4.setPhoneNumber("01-555-4564");
        customer4.setAddressLineOne("Alley 5 , Street 5");
        customer4.setAddressLineTwo("Alley 2, Street 5");
        customer4.setCity("Los Angles");
        customer4.setZipCode("987657");
        Customer customer5 = new Customer();
        customer5.setId(5);
        customer5.setFirstName("Steve");
        customer5.setLastName("Rogers");
        customer5.setPhoneNumber("01-555-4564");
        customer5.setAddressLineOne("Alley 5 , Street 5");
        customer5.setAddressLineTwo("Alley 2, Street 5");
        customer5.setCity("West Virgina");
        customer5.setZipCode("688767");
        customers.put(1, customer1);
        customers.put(2, customer2);
        customers.put(3, customer3);
        customers.put(4, customer4);
        customers.put(5, customer5);

    }
}
