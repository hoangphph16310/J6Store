package com.example.j6jav.service;

import com.example.j6jav.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;


public interface OrderService {
    Order create(JsonNode orderData);

    Object findById(Long id);

    List<Order> findByUserName(String username);
}
