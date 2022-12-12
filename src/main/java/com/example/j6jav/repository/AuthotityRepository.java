package com.example.j6jav.repository;

import com.example.j6jav.entity.Account;
import com.example.j6jav.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthotityRepository extends JpaRepository<Authority,Integer> {
    @Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
    List<Authority> authoritiesOf(List<Account> accounts);
}
