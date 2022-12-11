package com.example.j6jav.controller;

import com.example.j6jav.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;
    //Check out đặt hàng
    @GetMapping("checkout")
    public String checkout() {
        return "order/checkout";
    }

    //liệt kê đơn hàng đã đặt
    @GetMapping("list")
    public String list(Model model , HttpServletRequest req) {
        String username = req.getRemoteUser();
    model.addAttribute("orders",orderService.findByUserName(username));
        return "order/list";
    }

    //Xem lại chi tiết các đơn hàng đã đặt
    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order",orderService.findById(id));
        return "order/detail";
    }

}
