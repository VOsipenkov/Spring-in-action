package com.example.tacocloud.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    private String id;

    private String name;
    private String type;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private List<Taco> tacos;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
