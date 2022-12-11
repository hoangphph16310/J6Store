package com.example.j6jav.repository;

import com.example.j6jav.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.account.username=?1")
    List<Order> findByUserName(String username);
}
