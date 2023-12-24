package si.feri.okusisvet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import si.feri.okusisvet.dtomodel.recipe.CreateRecipeDto;
import si.feri.okusisvet.dtomodel.recipe.DetailedRecipeDto;
import si.feri.okusisvet.service.RecipeService;

import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    public UUID createRecipe(@RequestBody CreateRecipeDto recipe) {
        return recipeService.addRecipe(recipe);
    }

    @GetMapping("/{id}")
    public DetailedRecipeDto getRecipe(@PathVariable("id") UUID recipeId) {
        return recipeService.getDetailedRecipe(recipeId);
    }
}
