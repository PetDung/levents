package com.nhom_6.duan_1.controller.controllers;

import com.nhom_6.duan_1.model.entity.*;
import com.nhom_6.duan_1.serviceImp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductSeviceImp productSeviceImp;
    @Autowired
    private CategoryServiceImp categoryServiceImp;
    @Autowired
    private SizeServiceImp sizeServiceImp;
    @Autowired
    private ProductDetailsServiceImp productDetailsServiceImp;
    @Autowired
    private ColorServiceImp colorServiceImp;
    private List<Product> list = new ArrayList<>();

    @GetMapping("")
    public String shop(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                       @RequestParam(name = "categoryId", defaultValue = "-1") Long categoryId,
                       @RequestParam(name = "start", defaultValue = "0") Integer start,
                       @RequestParam(name = "end", defaultValue = "0") Integer end,
                       @RequestParam(name = "size", defaultValue = "no") String sizeName,
                       @RequestParam(name = "color", defaultValue = "no") String colorName, Model model) {

        Page<Product> products = productSeviceImp.getAll(pageNo);
        list = productSeviceImp.getAll(pageNo).stream().toList();
        model.addAttribute("page", "shop");
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listCategory", categoryServiceImp.getAll());
        // category
        if (categoryServiceImp.getById(categoryId).isPresent()) {
            list = categoryServiceImp.getById(categoryId).get().getProducts();
        }
        // lọc giá
        if (start >= 0 && end > 0) {
            list = productSeviceImp.getProductByPrice(start, end);
        }

        // lọc size
        if (!sizeName.equals("no")) {
            Size size = sizeServiceImp.getByName(sizeName);
            if (size != null) {
                List<ProductDetail> productDetails = productDetailsServiceImp.getBySizeId(size.getId());
                list = new ArrayList<>();
                for (Product i : productSeviceImp.getAll()) {
                    for (ProductDetail j : productDetails) {
                        if (i.getId() == j.getProduct().getId()) {
                            list.add(i);
                            break;
                        }
                    }
                }
            } else {
                list = new ArrayList<>();
            }
        }
        // lọc màu
        if (!colorName.equals("no")) {
            Color color = colorServiceImp.getByName(colorName);
            if (color != null) {
                List<ProductDetail> productDetails = productDetailsServiceImp.getByIdColor(color.getId());
                list = new ArrayList<>();
                for (Product i : productSeviceImp.getAll()) {
                    for (ProductDetail j : productDetails) {
                        if (i.getId() == j.getProduct().getId()) {
                            list.add(i);
                            break;
                        }
                    }
                }
            } else {
                list = new ArrayList<>();
            }
        }
        // lọc sắp xếp

        model.addAttribute("listProduct", list);
        return "layout/index";
    }
}
