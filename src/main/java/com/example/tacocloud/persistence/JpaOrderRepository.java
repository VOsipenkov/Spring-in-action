package com.example.tacocloud.persistence;

import com.example.tacocloud.model.jpa.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Long>, OrderRepository {

    List<Order> findByDeliveryZip(String deliveryZip);

    List<Order> findOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date start, Date end);
}
