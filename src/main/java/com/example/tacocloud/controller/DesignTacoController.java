package com.example.tacocloud.controller;

import com.example.tacocloud.model.Ingredient;
import com.example.tacocloud.model.Taco;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.tacocloud.model.Ingredient.Type.*;

@Data
@Controller
@RequestMapping(value = "/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        var ingredients = Arrays.asList(
            new Ingredient("FLTO", "Flour Tortilla", WRAP),
            new Ingredient("COTO", "Corn Tortilla", WRAP),
            new Ingredient("GRBF", "Ground Beef", PROTEIN),
            new Ingredient("CARN", "Carnitas", PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", VEGGIES),
            new Ingredient("LETC", "Lettuce", VEGGIES),
            new Ingredient("CHED", "Cheddar", CHEESE),
            new Ingredient("JACK", "Monterrey Jack", CHEESE),
            new Ingredient("SLSA", "Salsa", SAUCE),
            new Ingredient("SRCR", "Sour Cream", SAUCE)
        );

        for (var type : Ingredient.Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredientList, Ingredient.Type type) {
        return ingredientList.parallelStream()
            .filter(i -> Objects.equals(type, i.getType()))
            .collect(Collectors.toList());
    }
}
