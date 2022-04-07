package com.example.tacocloud.model.hateoas;

import com.example.tacocloud.controller.DesignTacoControllerV3;
import com.example.tacocloud.model.jpa.Taco;
import lombok.Data;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Data
@Relation(itemRelation = "taco", collectionRelation = "tacos")
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
