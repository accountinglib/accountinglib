package org.accountinglib.controller;


import org.accountinglib.model.Customer;
import org.accountinglib.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for listing customers.
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/customers")
    public String listCustomers(Model model) {

        Iterable<Customer> customers = customerRepository.findAll();

        model.addAttribute("customers", customers);

        return "customerList"; // This corresponds to the name of the HTML template
    }
}
