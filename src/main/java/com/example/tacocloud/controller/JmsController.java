package com.example.tacocloud.controller;

import com.example.tacocloud.model.jpa.Order;
import com.example.tacocloud.persistence.JpaOrderRepository;
import com.example.tacocloud.persistence.JpaTacoRepository;
import com.example.tacocloud.service.JmsOrderingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/jms")
public class JmsController {

    private final JmsOrderingService jmsOrderingService;
    private final JpaOrderRepository jpaOrderRepository;
    private final JpaTacoRepository jpaTacoRepository;

    @ResponseBody
    @GetMapping("/order/send")
    public void sendOrder() {
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by("placedAt").descending());
        var order = jpaOrderRepository.findAll(pageRequest).getContent();
        jmsOrderingService.send(order.get(0));
        log.info("Order sent..");
    }

    @ResponseBody
    @GetMapping("/order/receive/pull")
    public Order receiveOrderPull() {
        var order = jmsOrderingService.receivePull();
        log.info("Received pull response");
        return order;
    }

    @ResponseBody
    @GetMapping("/taco/send")
    public void sendTaco() {
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by("createdAt").descending());
        var taco = jpaTacoRepository.findAll(pageRequest).getContent().get(0);
        jmsOrderingService.send(taco);
        log.info("Taco sent..");
    }
}
