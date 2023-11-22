package com.nhom_6.duan_1.controller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactsController {

    @GetMapping
    public String contacts(Model model) {
        model.addAttribute("page", "contact");
        return "layout/index";
    }
}
