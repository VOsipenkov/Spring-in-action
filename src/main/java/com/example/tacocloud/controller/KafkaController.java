package com.example.tacocloud.controller;

import com.example.tacocloud.model.jpa.Order;
import com.example.tacocloud.model.jpa.Taco;
import com.example.tacocloud.persistence.JpaOrderRepository;
import com.example.tacocloud.persistence.JpaTacoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final JpaOrderRepository jpaOrderRepository;
    private final JpaTacoRepository jpaTacoRepository;
    private final KafkaTemplate<String, Order> orderKafkaTemplate;
    private final KafkaTemplate<String, Taco> tacoKafkaTemplate;
    private final ApplicationContext applicationContext;

    @ResponseBody
    @GetMapping(value = "/order/send")
    public void sendOrder() {
        var pageRequest = PageRequest.of(0, 1, Sort.by("placedAt").descending());
        var order = jpaOrderRepository.findAll(pageRequest).getContent().get(0);
        orderKafkaTemplate.send("tacocloud.orders.topic", "one", order);
        log.info("Order sent to Kafka");
    }

    @ResponseBody
    @GetMapping(value = "/taco/send")
    public void sendTaco() {
        var pageRequest = PageRequest.of(0, 1, Sort.by("createdAt").descending());
        var taco = jpaTacoRepository.findAll(pageRequest).getContent().get(0);
        tacoKafkaTemplate.send("tacocloud.tacos.topic", "two", taco);
        log.info("Taco sent to Kafka");
    }
}
