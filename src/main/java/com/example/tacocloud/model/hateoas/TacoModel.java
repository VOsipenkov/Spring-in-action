package com.example.tacocloud.model.hateoas;

import com.example.tacocloud.controller.DesignTacoControllerV3;
import com.example.tacocloud.model.jpa.Taco;
import lombok.Data;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
public class TacoModel extends RepresentationModel<TacoModel> {

    private static IngredientModelAssembler ingredientModelAssembler =
        new IngredientModelAssembler(DesignTacoControllerV3.class, IngredientModel.class);

    private String name;
    private LocalDateTime createdAt;
    private CollectionModel ingredients;

    public TacoModel(Taco taco) {
        name = taco.getName();
        createdAt = taco.getCreatedAt();
        ingredients = ingredientModelAssembler.toCollectionModel(taco.getIngredients());
    }
}
