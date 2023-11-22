package com.nhom_6.duan_1.controller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product-details")
public class ShopDetailsController {

    @GetMapping
    public String productDetails(Model model) {
        model.addAttribute("page", "product-details");
        return "layout/index";
    }
}
