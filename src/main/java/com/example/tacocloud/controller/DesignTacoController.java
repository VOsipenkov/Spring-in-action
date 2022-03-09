package com.example.tacocloud.controller;

import com.example.tacocloud.model.Ingredient;
import com.example.tacocloud.model.Taco;
import com.example.tacocloud.persistence.IngredientRepository;
import com.example.tacocloud.persistence.TacoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/design")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @GetMapping
    public String showDesignForm(Model model) {
        init(model);
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(Model model, @Valid Taco design, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Errors occurs");
            init(model);
            model.addAttribute("design", design);
            return "design";
        }
        log.info(design.toString());
        var res = tacoRepository.save(design);

        return "redirect:/order/current";
    }

    private void init(Model model) {
        var ingredients = new ArrayList<Ingredient>();
        ingredientRepository.findAll().forEach(ingredients::add);
        for (var type : Ingredient.Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredientList, Ingredient.Type type) {
        return ingredientList.parallelStream()
            .filter(i -> Objects.equals(type, i.getType()))
            .collect(Collectors.toList());
    }
}
