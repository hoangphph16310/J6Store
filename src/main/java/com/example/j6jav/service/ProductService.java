package com.example.j6jav.service;

import com.example.j6jav.entity.Product;

import java.util.List;
import java.util.Optional;

//chứa các phương thức thực hiện những mong muoons trong controller
public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    List<Product> findByCategoryId(String cid);
}
