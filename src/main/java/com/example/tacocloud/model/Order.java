package com.example.tacocloud.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Order {
    private String name;
    private String city;
    private String street;
    private String state;
    private String zip;

    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
}
