package com.aliseylaneh.springmvcspringboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {
    @RequestMapping("/customers")
    public String customers() {
        return "customers";
    }
}
