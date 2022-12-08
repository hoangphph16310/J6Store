package com.example.j6jav.interceptor;

import com.example.j6jav.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GloballInterceptor implements HandlerInterceptor {
    @Autowired
    CategoryService categoryService;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("category",categoryService.findAll());
        System.out.println(categoryService.findAll().size());
    }
//    sau khi thực hiện phưng thức trong controller thì post handle mới chạy
}
