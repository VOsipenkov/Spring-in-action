package com.example.tacocloud.config;

import com.example.tacocloud.model.jpa.Order;
import com.example.tacocloud.model.jpa.Taco;
import com.example.tacocloud.service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Configuration
@EnableKafka
@EnableConfigurationProperties
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaService kafkaService;

    // Order
    @Bean
    @ConfigurationProperties("spring.kafka.producer.order")
    public Properties orderProducerProperties() {
        return new Properties();
    }

    @Bean
    @ConfigurationProperties("spring.kafka.consumer.order")
    public Properties orderConsumerProperties() {
        return new Properties();
    }

    @Bean
    @ConfigurationProperties("spring.kafka.consumer.log-event")
    public Properties logEventConsumerProperties() {
        return new Properties();
    }

    @Bean
    public ProducerFactory<String, Order> orderProducerFactory(Properties orderProducerProperties) {
        Map<String, Object> props = new HashMap<>();
        orderProducerProperties.stringPropertyNames().forEach(p -> props.put(p, orderProducerProperties.get(p)));
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public DefaultKafkaConsumerFactory<Object, Order> orderConsumerFactory(Properties orderConsumerProperties) {
        Map<String, Object> props = new HashMap<>();
        orderConsumerProperties.stringPropertyNames().forEach(p -> props.put(p, orderConsumerProperties.get(p)));
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Order> orderKafkaTemplate(ProducerFactory<String, Order> orderProducerFactory) {
        return new KafkaTemplate<>(orderProducerFactory);
    }

    @Bean
    public MessageListener orderMessageListener() {
        return o -> kafkaService.checkOrder();
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, Order> orderKafkaMessageListenerContainer(
        Properties orderConsumerProperties, DefaultKafkaConsumerFactory orderConsumerFactory) {
        var containerProperties = new ContainerProperties(new String[]{(String) orderConsumerProperties.get("topic")});
        containerProperties.setMessageListener(orderMessageListener());
        containerProperties.setGroupId("first");
        return new ConcurrentMessageListenerContainer(orderConsumerFactory, containerProperties);
    }

    //     Taco
    @Bean
    @ConfigurationProperties("spring.kafka.producer.taco")
    public Properties tacoProducerProperties() {
        return new Properties();
    }

    @Bean
    @ConfigurationProperties("spring.kafka.consumer.taco")
    public Properties tacoConsumerProperties() {
        return new Properties();
    }

    @Bean
    public DefaultKafkaProducerFactory tacoProducerFactory(Properties tacoProducerProperties) {
        var props = new HashMap<String, Object>();
        tacoProducerProperties.stringPropertyNames().forEach(p -> props.put(p, tacoProducerProperties.get(p)));
        return new DefaultKafkaProducerFactory<String, Taco>(props);
    }

    @Bean
    public DefaultKafkaConsumerFactory<String, Taco> tacoConsumerFactory(Properties tacoConsumerProperties) {
        var props = new HashMap<String, Object>();
        tacoConsumerProperties.stringPropertyNames().forEach(p -> props.put(p, tacoConsumerProperties.get(p)));
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public KafkaTemplate tacoKafkaTemplate(DefaultKafkaProducerFactory tacoProducerFactory) {
        return new KafkaTemplate<>(tacoProducerFactory);
    }

    @Bean
    public MessageListener<String, Taco> tacoConsumerListener() {
        return (o)->kafkaService.checkTaco(o);
    }

    @Bean
    public KafkaMessageListenerContainer<String, Taco> tacoConcurrentMessageListenerContainer(
        Properties tacoConsumerProperties, DefaultKafkaConsumerFactory<String, Taco> tacoConsumerFactory) {
        var containerProperties = new ContainerProperties((String) tacoConsumerProperties.get("topic"));
        containerProperties.setGroupId("taco-id");
        containerProperties.setMessageListener(tacoConsumerListener());
        return new KafkaMessageListenerContainer<>(tacoConsumerFactory, containerProperties);
    }
}
