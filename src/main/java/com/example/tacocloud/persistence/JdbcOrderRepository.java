package com.example.tacocloud.persistence;

import com.example.tacocloud.model.jdbc.Order;
import com.example.tacocloud.model.jdbc.Taco;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Primary
@Repository
@RequiredArgsConstructor
public class JdbcOrderRepository implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    private static final String TACO_ORDER_TABLE = "Taco_Order";
    private static final String TACO_ORDER_TACOS_TABLE = "Taco_Order_Tacos";

    @PostConstruct
    public void init() {
        orderInserter = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName(TACO_ORDER_TABLE)
            .usingGeneratedKeyColumns("id");
        orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName(TACO_ORDER_TACOS_TABLE);
        objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        var id = saveOrderDetails(order);
        order.setId(id);
        order.getTacoList().forEach(taco -> saveTacoToOrder(id, taco));
        return order;
    }

    private Long saveOrderDetails(Order order) {
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", Timestamp.valueOf(LocalDateTime.now()));
        return orderInserter.executeAndReturnKey(values).longValue();
    }

    private void saveTacoToOrder(Long id, Taco taco) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", id);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }
}
