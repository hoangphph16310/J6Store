package com.example.j6jav.service;

import com.example.j6jav.entity.Account;
import com.example.j6jav.entity.Authority;
import com.example.j6jav.repository.AccountRepository;
import com.example.j6jav.repository.AuthotityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImp implements AuthorityService {
    @Autowired
    AccountRepository accountRepo;
    @Autowired
    AuthotityRepository authotityRepo;
    @Override
    public List<Authority> findAuthoritiesOfAdministrator() {
        List<Account> accounts = accountRepo.getAdministrators();
        return authotityRepo.authoritiesOf(accounts);
    }

    @Override
    public List<Authority> findAll() {
        return null;
    }

    @Override
    public Authority create(Authority auth) {
        return authotityRepo.save(auth);
    }

    @Override
    public void delete(Integer id) {
        authotityRepo.deleteById(id);
    }
}
