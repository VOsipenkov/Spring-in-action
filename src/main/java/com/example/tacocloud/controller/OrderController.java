package com.example.tacocloud.controller;

import com.example.tacocloud.model.jpa.Ingredient;
import com.example.tacocloud.model.jpa.Order;
import com.example.tacocloud.persistence.JpaOrderRepository;
import com.example.tacocloud.persistence.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

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
    public String processOrder(@Valid com.example.tacocloud.model.jdbc.Order order, Errors errors, SessionStatus sessionStatus, Principal principal) {
        return processOrder(map(order), errors, sessionStatus, principal);
    }

    @PostMapping(headers = {"version=v2"})
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

    private Order map(com.example.tacocloud.model.jdbc.Order jdbcOrder) {
        var jpaOrder = new Order();
        jpaOrder.setId(jdbcOrder.getId());
        jpaOrder.setDeliveryName((jdbcOrder.getDeliveryName()));
        jpaOrder.setDeliveryStreet(jdbcOrder.getDeliveryStreet());
        jpaOrder.setDeliveryCity(jdbcOrder.getDeliveryCity());
        jpaOrder.setDeliveryState(jdbcOrder.getDeliveryState());
        jpaOrder.setDeliveryZip(jdbcOrder.getDeliveryZip());
        jpaOrder.setCcNumber(jdbcOrder.getCcNumber());
        jpaOrder.setCcExpiration(jdbcOrder.getCcExpiration());
        jpaOrder.setCcCvv(jdbcOrder.getCcCVV());
        jpaOrder.setPlacedAt(nonNull(jdbcOrder.getPlacedAt()) ? jdbcOrder.getPlacedAt().toLocalDateTime() : null);
        if (!CollectionUtils.isEmpty(jdbcOrder.getTacoList())) {
            jpaOrder.setTacos(
                jdbcOrder.getTacoList()
                    .stream()
                    .map(jdbcTaco -> map(jdbcTaco))
                    .collect(Collectors.toList()));
        }
        return jpaOrder;
    }

    private com.example.tacocloud.model.jpa.Taco map(com.example.tacocloud.model.jdbc.Taco jdbcTaco) {
        final Random random = new Random(10);
        var jpaTaco = new com.example.tacocloud.model.jpa.Taco();
        jpaTaco.setId(jdbcTaco.getId());
        jpaTaco.setName(jdbcTaco.getName());
        jpaTaco.setCreatedAt(nonNull(jdbcTaco.getCreatedAt()) ?
            jdbcTaco.getCreatedAt().toLocalDate().atTime(0, 0) : null);
        if (!CollectionUtils.isEmpty(jdbcTaco.getIngredients())) {
            jpaTaco.setIngredients(jdbcTaco.getIngredients().stream().map(jdbcI -> {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(random.nextLong());
                ingredient.setName(jdbcI);
                return ingredient;
            }).collect(Collectors.toList()));
        }
        return jpaTaco;
    }
}
