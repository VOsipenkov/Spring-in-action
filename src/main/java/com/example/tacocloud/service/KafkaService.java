package com.example.tacocloud.service;

import com.example.tacocloud.model.jpa.Taco;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableKafka
public class KafkaService {

    @KafkaListener(topics = "tacocloud.orders.topic", groupId = "one")
    public void order() {
        System.out.println("=========================Order=========================");
    }

    @KafkaListener(topics = "tacocloud.tacos.topic", groupId = "two")
    public void taco() {
        System.out.println("=========================Taco=========================");
    }

    public void checkOrder() {
        System.out.println("=========================Order2=========================");
    }

    public void checkTaco(ConsumerRecord<String, Taco> record) {
        System.out.println("=========================Taco2=========================");
        log.info("received taco {}", record.value());
    }
}
