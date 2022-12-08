package com.example.j6jav.rest.controller;

import com.example.j6jav.entity.Product;
import com.example.j6jav.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//ngăn mã JavaScript tạo ra
@CrossOrigin("*")
@RestController
@RequestMapping("rest/products")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Integer id){
        return productService.findById(id);
    }
}
