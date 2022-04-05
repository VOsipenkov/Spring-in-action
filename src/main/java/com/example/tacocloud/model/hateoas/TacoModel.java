package com.example.tacocloud.model.hateoas;

import com.example.tacocloud.model.jpa.Ingredient;
import com.example.tacocloud.model.jpa.Taco;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TacoModel extends RepresentationModel<TacoModel> {

    private String name;
    private LocalDateTime createdAt;
    private List<Ingredient> ingredients;

    public TacoModel(Taco taco) {
        name = taco.getName();
        createdAt = taco.getCreatedAt();
        ingredients = taco.getIngredients();
    }
}
