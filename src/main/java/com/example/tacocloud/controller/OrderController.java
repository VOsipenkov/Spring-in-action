package com.example.tacocloud.controller;

import com.example.tacocloud.model.Order;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Slf4j
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @GetMapping(value = "/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(Order order) {
        log.info("Order submitted: {}", order);
        return "redirect:/";
    }
}
