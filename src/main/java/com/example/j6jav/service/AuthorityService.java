package com.example.j6jav.service;

import com.example.j6jav.entity.Authority;

import java.util.List;

public interface AuthorityService {

    List<Authority> findAuthoritiesOfAdministrator();


    List<Authority> findAll();

    Authority create(Authority auth);

    void delete(Integer id);
}
