package com.example.tacocloud.service;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaService {

    @KafkaListener(topics = "tacocloud.orders.topic", groupId = "one")
    public void order() {
        System.out.println("=========================Order=========================");
    }

    @KafkaListener(topics ="tacocloud.tacos.topic", groupId = "two")
    public void taco() {
        System.out.println("=========================Taco=========================");
    }
}
