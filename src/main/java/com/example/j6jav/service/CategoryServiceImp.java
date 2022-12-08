package com.example.j6jav.service;

import com.example.j6jav.entity.Category;
import com.example.j6jav.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService{
@Autowired
    CategoryRepository categoryRepo;

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }
}
