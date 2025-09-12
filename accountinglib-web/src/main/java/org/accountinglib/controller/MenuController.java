package org.accountinglib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the main start page.
 */
@Controller
public class MenuController {

    @GetMapping("/")
    public String menu(Model model) {
        return "menu"; // This corresponds to the name of the HTML template
    }
}
