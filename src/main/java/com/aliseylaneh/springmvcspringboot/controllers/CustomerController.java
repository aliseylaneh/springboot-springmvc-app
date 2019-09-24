package com.aliseylaneh.springmvcspringboot.controllers;

import com.aliseylaneh.springmvcspringboot.domain.Customer;
import com.aliseylaneh.springmvcspringboot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers", customerService.listAllCustomers());
        return "customers";
    }

}
