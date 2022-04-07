package com.example.tacocloud.model.hateoas;

import com.example.tacocloud.model.jpa.Ingredient;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Relation(itemRelation = "ingredient", collectionRelation = "ingredients")
public class IngredientModel extends RepresentationModel<IngredientModel> {

    private String name;
    private String type;

    public IngredientModel(Ingredient ingredient) {
        name = ingredient.getName();
        type = ingredient.getType();
    }
}
