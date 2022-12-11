package com.example.j6jav.rest.controller;

import com.example.j6jav.entity.Order;
import com.example.j6jav.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("rest")
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @PostMapping("orders")
    public Order create(@RequestBody JsonNode orderData){
        return orderService.create(orderData);
    }
}
