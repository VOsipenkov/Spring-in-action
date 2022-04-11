package com.example.tacocloud.controller;

import com.example.tacocloud.model.jpa.Order;
import com.example.tacocloud.persistence.JpaOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
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
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @ResponseBody
    @GetMapping(value = "/order/send")
    public void send() {
        var pageRequest = PageRequest.of(0, 1, Sort.by("placedAt").descending());
        var order = jpaOrderRepository.findAll(pageRequest).getContent().get(0);
        kafkaTemplate.send("tacocloud.orders.topic", "one",  order);
        log.info("Order sent to Kafka");
    }

    @KafkaListener(topics = "tacocloud.orders.topic", groupId = "1")
    public void receiveOrder(Order order) {
        log.info("Order received from Kafka {}", order);
    }
}
