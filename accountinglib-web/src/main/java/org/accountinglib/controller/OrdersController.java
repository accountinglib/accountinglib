package org.accountinglib.controller;

import org.accountinglib.model.OrderLine;
import org.accountinglib.model.Orders;
import org.accountinglib.repository.OrderLineRepository;
import org.accountinglib.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controller for listing orders.
 */
@Controller
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @GetMapping("/orders")
    public String listOrders(Model model) {

        Iterable<Orders> orders = ordersRepository.findAll();

        model.addAttribute("orders", orders);

        return "ordersList"; // This corresponds to the name of the HTML template
    }
}
