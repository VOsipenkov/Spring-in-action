package com.example.tacocloud.controller;

import com.example.tacocloud.model.jpa.Order;
import com.example.tacocloud.persistence.JpaOrderRepository;
import com.example.tacocloud.persistence.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;

@Data
@Slf4j
@Controller
@RequestMapping(value = "/order")
@SessionAttributes("order")
@RequiredArgsConstructor
public class OrderController {

    private final JpaOrderRepository jpaOrderRepository;
    private final UserRepository userRepository;

    @GetMapping(value = "/current")
    public String orderForm(Model model) {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, Principal principal) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        var user = userRepository.findUserByUsername(principal.getName());
        order.setUser(user);
        jpaOrderRepository.save(order);

        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
