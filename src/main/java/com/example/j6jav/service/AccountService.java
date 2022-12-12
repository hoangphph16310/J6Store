package com.example.j6jav.service;

import com.example.j6jav.entity.Account;

import java.util.List;

public interface AccountService {
    Account findById(String username);

    List<Account> getAdministrators();

    List<Account> findAll();
}
