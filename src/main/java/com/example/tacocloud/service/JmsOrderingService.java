package com.example.tacocloud.service;

import com.example.tacocloud.model.jpa.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public Order receivePull() {
        var message = jmsTemplate.receiveAndConvert();
        var order = (Order) message;
        log.info("Received message {}", order);
        return order;
    }
}
