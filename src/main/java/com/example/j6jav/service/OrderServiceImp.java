package com.example.j6jav.service;

import aj.org.objectweb.asm.TypeReference;
import com.example.j6jav.entity.Order;
import com.example.j6jav.entity.OrderDetail;
import com.example.j6jav.repository.OrderDetailRepository;
import com.example.j6jav.repository.OrderRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderDetailRepository orderDetailRepo;
    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();

        Order order = mapper.convertValue(orderData,Order.class);
        orderRepo.save(order);


//        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>(){};
//        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"),type).stream().peek(d->d.setOrder(order).collect(Collectors.toList()));
//        orderDetailRepo.saveAll(details);
        return order;
    }
}
