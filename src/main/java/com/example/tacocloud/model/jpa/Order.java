package com.example.tacocloud.model.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Taco_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "placed_at")
    private LocalDateTime placedAt;
    @Column(name = "delivery_name")
    private String deliveryName;
    @Column(name = "delivery_street")
    private String deliveryStreet;
    @Column(name = "delivery_city")
    private String deliveryCity;
    @Column(name = "delivery_state")
    private String deliveryState;
    @Column(name = "delivery_zip")
    private String deliveryZip;
    @Column(name = "cc_number")
    private String ccNumber;
    @Column(name = "cc_expiration")
    private String ccExpiration;
    @Column(name = "cc_cvv")
    private String ccCvv;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos;

    @ManyToOne
    private User user;

    public void addTaco(Taco taco) {
        if (CollectionUtils.isEmpty(tacos)) {
            tacos = new ArrayList<>();
        }
        tacos.add(taco);
    }

    @PrePersist
    void plateAt() {
        this.placedAt = LocalDateTime.now();
    }
}
