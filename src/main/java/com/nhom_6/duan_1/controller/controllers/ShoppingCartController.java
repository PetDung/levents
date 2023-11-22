package com.nhom_6.duan_1.controller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @GetMapping
    public String shoppingCart(Model model) {
        model.addAttribute("page", "shopping-cart");
        return "layout/index";
    }
}
