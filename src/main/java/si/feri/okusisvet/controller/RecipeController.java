package si.feri.okusisvet.controller;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import si.feri.okusisvet.dtomodel.PageInfoRequest;
import si.feri.okusisvet.dtomodel.recipe.CreateRecipeDto;
import si.feri.okusisvet.dtomodel.recipe.DetailedRecipeDto;
import si.feri.okusisvet.dtomodel.recipe.ListRecipeResponse;
import si.feri.okusisvet.dtomodel.recipe.RecipeSearchParams;
import si.feri.okusisvet.dtomodel.recipe.RecipeSortInfoRequest;
import si.feri.okusisvet.service.RecipeService;

import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    public UUID createRecipe(@RequestBody CreateRecipeDto recipe, HttpServletRequest request, HttpServletResponse response) {
        UUID id =  recipeService.addRecipe(recipe, request);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return id;
    }

    @GetMapping("{id}/publish")
    public void publishRecipe(@PathVariable("id") UUID recipeId, HttpServletRequest request) {
        recipeService.publishRecipe(recipeId, request);
    }

    @GetMapping("{id}")
    public DetailedRecipeDto getRecipe(@PathVariable("id") UUID recipeId, HttpServletRequest request) {
        return recipeService.getDetailedRecipe(recipeId, request);
    }

    @GetMapping("list")
    public ListRecipeResponse listRecipes(@NotNull PageInfoRequest pageInfo,
                                          @Nullable RecipeSortInfoRequest sortInfo,
                                          @Nullable RecipeSearchParams searchParams
    ) {
        return ListRecipeResponse.fromPage(
                recipeService.searchRecipes(pageInfo, sortInfo, searchParams)
        );
    }
}
