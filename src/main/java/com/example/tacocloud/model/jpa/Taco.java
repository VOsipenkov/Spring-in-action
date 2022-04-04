package com.example.tacocloud.model.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Taco")
@ToString
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
        name="Taco_Ingredients",
        joinColumns = @JoinColumn(name="taco"),
        inverseJoinColumns = @JoinColumn(name="ingredient")
    )
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

}
