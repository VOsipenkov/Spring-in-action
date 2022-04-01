package com.example.tacocloud.model.jdbc;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Order {

    private Long id;

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private Timestamp placedAt = Timestamp.valueOf(LocalDateTime.now());
    private List<Taco> tacoList;

    public void addTaco(Taco taco) {
        if (CollectionUtils.isEmpty(tacoList)) {
            tacoList = new ArrayList<>();
        }
        tacoList.add(taco);
    }
}
