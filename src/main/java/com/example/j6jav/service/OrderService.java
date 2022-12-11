package com.example.j6jav.service;

import com.example.j6jav.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {
    Order create(JsonNode orderData);
}
