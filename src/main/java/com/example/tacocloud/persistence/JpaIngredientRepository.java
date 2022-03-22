package com.example.tacocloud.persistence;

import com.example.tacocloud.model.jpa.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaIngredientRepository extends JpaRepository<Ingredient, Long>{
}
