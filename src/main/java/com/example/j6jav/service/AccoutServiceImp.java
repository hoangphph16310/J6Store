package com.example.j6jav.service;

import com.example.j6jav.entity.Account;
import com.example.j6jav.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccoutServiceImp implements AccountService{
    @Autowired
    AccountRepository accountRepo;

    @Override
    public Account findById(String username) {
        return accountRepo.findById(username).get();
    }

    @Override
    public List<Account> getAdministrators() {
        return accountRepo.getAdministrators();
    }

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }
}
