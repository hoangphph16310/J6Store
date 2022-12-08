package com.example.j6jav.controller;

import com.example.j6jav.entity.Product;
import com.example.j6jav.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("list")
    public String list(Model model, @RequestParam("cid") Optional<String> cid) {
        //lọc
        if (cid.isPresent()) {
            List<Product> list = productService.findByCategoryId(cid.get());
            model.addAttribute("items", list);

        } else {
            //Lấy toàn bộ sản phẩm
            List<Product> list = productService.findAll();
            model.addAttribute("items", list);
        }
        return "product/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Product item = productService.findById(id);
        model.addAttribute("item", item);
        return "product/detail";
    }
}
