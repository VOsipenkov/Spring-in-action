package com.example.tacocloud.service;

import com.example.tacocloud.model.jpa.Order;
import com.example.tacocloud.model.jpa.Taco;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JmsOrderingService {

    private final JmsTemplate jmsTemplate;

    public void send(Order order) {
        jmsTemplate.convertAndSend("tacocloud.order.queue", order);
    }

    public void send(Taco taco) {
        jmsTemplate.convertAndSend("tacocloud.taco.queue", taco);
    }

    public Order receivePull() {
        var message = jmsTemplate.receiveAndConvert();
        var order = (Order) message;
        log.info("Received message {}", order);
        return order;
    }

    @JmsListener(destination = "tacocloud.taco.queue")
    public void receivePush(Taco taco) {
        log.info("Received taco {}", taco);
    }
}
