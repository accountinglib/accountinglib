package org.accountinglib.controller;

import org.accountinglib.dto.OrderDTO;
import org.accountinglib.dto.OrderLineDTO;
import org.accountinglib.dto.ProductDTO;
import org.accountinglib.model.Customer;
import org.accountinglib.model.OrderLine;
import org.accountinglib.model.Orders;
import org.accountinglib.model.Product;
import org.accountinglib.repository.CustomerRepository;
import org.accountinglib.repository.OrderLineRepository;
import org.accountinglib.repository.OrdersRepository;

import org.accountinglib.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * API controller for creating and getting Orders.
 */
@Controller
@RestController
@RequestMapping(path="/api")
public class APIController {

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path="/createOrder")
    public @ResponseBody String createOrder (@RequestParam Integer customerId
            ,@RequestParam String productIds) throws Exception {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new Exception("Customer not found"));

		Orders order = new Orders();
		order.setCustomer(customer);
		order.setOrderDate(new Date());

		ordersRepository.save(order);

		if (productIds == null || productIds.isEmpty()) throw new Exception("Invalid productIds.");

        for (String productId : productIds.split(",")) {
           Product product = productRepository.findById(Integer.parseInt(productId)).orElseThrow(() -> new Exception("Product not found"));

           OrderLine orderLine = new OrderLine();
		   orderLine.setOrder(order);
           orderLine.setProduct(product);
           orderLine.setPrice(product.getPrice());
           orderLine.setFinalPrice(product.getFinalPrice(customer));
           orderLine.setDiscount(product.getPrice() - product.getFinalPrice(customer));
           orderLineRepository.save(orderLine);
        }


        return "Saved with id " + order.getId();
    }


    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrdersById(@PathVariable Integer id) {
        Optional<Orders> orders = ordersRepository.findById(id);

        if (orders.isPresent()) {
            return ResponseEntity.ok(convertToDTO(orders.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private OrderDTO convertToDTO(Orders order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerId(order.getCustomer().getId());
        List<OrderLineDTO> orderLines = new ArrayList<>();

        for (OrderLine orderLine : order.getOrderLines()) {
            orderLines.add(convertToDTO(orderLine));
        }

        orderDTO.setOrderLines(orderLines);

        return orderDTO;
    }

    private OrderLineDTO convertToDTO(OrderLine orderLine) {
        OrderLineDTO orderLineDTO = new OrderLineDTO();
        orderLineDTO.setId(orderLine.getId());
        orderLineDTO.setProduct(convertToDTO(orderLine.getProduct()));
        orderLineDTO.setFinalPrice(orderLine.getFinalPrice());
        orderLineDTO.setDiscount(orderLine.getDiscount());
        orderLineDTO.setPrice(orderLine.getPrice());
        return orderLineDTO;
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());

        return productDTO;
    }
}
