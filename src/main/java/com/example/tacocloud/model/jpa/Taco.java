//package com.example.tacocloud.model.jpa;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.Date;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@Entity
//@NoArgsConstructor
//public class Taco {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @NotNull
//    @Size(min = 5, message = "Name must be at least 5 characters long")
//    private String name;
//
//    private Date createdAt;
//
//    @ManyToMany(targetEntity = Ingredient.class)
//    @Size(min = 1, message = "You must choose at least 1 ingredient")
//    private List<Ingredient> ingredients;
//
//    @PrePersist
//    void createdAt() {
//        createdAt = new Date();
//    }
//}
