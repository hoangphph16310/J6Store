package com.example.j6jav.service;

import com.example.j6jav.entity.Product;
import com.example.j6jav.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    ProductRepository productRepo;
    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> findByCategoryId(String cid) {
        return productRepo.findByCategoryId(cid);
    }
}
