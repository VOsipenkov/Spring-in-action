package com.example.tacocloud.controller;

import com.example.tacocloud.persistence.JpaOrderRepository;
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

    @ResponseBody
    @GetMapping("/order/send")
    public void getOrder() {
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by("placedAt").descending());
        var order = jpaOrderRepository.findAll(pageRequest).getContent();
        jmsOrderingService.send(order.get(0));
        log.info("Message sent..");
    }
}
