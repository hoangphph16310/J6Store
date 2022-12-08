package com.example.j6jav.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {
    //Check out đặt hàng
    @GetMapping("checkout")
    public String checkout() {
        return "order/checkout";
    }

    //liệt kê đơn hàng đã đặt
    @GetMapping("list")
    public String list() {
        return "order/list";
    }

    //Xem lại chi tiết các đơn hàng đã đặt
    @GetMapping("detail/{id}")
    public String detail() {
        return "order/detail";
    }

}
