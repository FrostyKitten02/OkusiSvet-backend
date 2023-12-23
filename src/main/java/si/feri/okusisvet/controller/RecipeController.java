package si.feri.okusisvet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import si.feri.okusisvet.dtomodel.recipe.CreateRecipe;
import si.feri.okusisvet.service.RecipeService;

import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    public UUID createRecipe(@RequestBody CreateRecipe recipe) {
        return recipeService.addRecipe(recipe);
    }
}
