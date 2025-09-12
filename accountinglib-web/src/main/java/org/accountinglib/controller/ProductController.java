package org.accountinglib.controller;


import org.accountinglib.model.Product;
import org.accountinglib.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controller for listing products.
 */
@Controller
public class ProductController {


    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/products")
    public String listProducts(Model model) {

        Iterable<Product> products = productRepository.findAll();


        model.addAttribute("products", products);

        return "productList"; // This corresponds to the name of the HTML template
    }
}