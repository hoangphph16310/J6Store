package com.example.j6jav.rest.controller;

import com.example.j6jav.entity.Product;
import com.example.j6jav.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//ngăn mã JavaScript tạo ra
@CrossOrigin("*")
@RestController
@RequestMapping("rest/products")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public List<Product> getAll(){
        return productService.findAll();
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Integer id){
        return productService.findById(id);
    }

    @PostMapping()
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }

    @PutMapping("{id}")
    public Product update(@PathVariable("id") String id, @RequestBody Product product){
        return productService.update(product);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        System.out.println("đến đây là dừng");
        productService.delete(id);

    }
}
