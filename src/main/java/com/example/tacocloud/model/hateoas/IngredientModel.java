package com.example.tacocloud.model.hateoas;

import com.example.tacocloud.model.jpa.Ingredient;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
@Data
public class IngredientModel extends RepresentationModel<IngredientModel> {

    private String name;
    private String type;

    public IngredientModel(Ingredient ingredient) {
        name = ingredient.getName();
        type = ingredient.getType();
    }
}
