//package com.example.tacocloud.model.jpa;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.util.CollectionUtils;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Data
//@Entity
//@NoArgsConstructor
//@Table(name = "Taco_Order")
//public class Order implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private Date placedAt;
//    private String deliveryName;
//    private String deliveryStreet;
//    private String deliveryCity;
//    private String deliveryState;
//    private String deliveryZip;
//    private String ccNumber;
//    private String ccExpiration;
//    private String ccCVV;
//
//    @ManyToMany(targetEntity = Taco.class)
//    private List<Taco> tacos;
//
//    public void addTaco(Taco taco) {
//        if (CollectionUtils.isEmpty(tacos)) {
//            tacos = new ArrayList<>();
//        }
//        tacos.add(taco);
//    }
//
//    @PrePersist
//    void plateAt() {
//        this.placedAt = new Date();
//    }
//}
