package com.example.tacocloud.persistence;

import com.example.tacocloud.model.jdbc.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
    Order save(Order order);
}
