package com.example.tacocloud.persistence;

import com.example.tacocloud.model.jdbc.Ingredient;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
