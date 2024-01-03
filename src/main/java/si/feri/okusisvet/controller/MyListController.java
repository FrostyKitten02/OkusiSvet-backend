package si.feri.okusisvet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import si.feri.okusisvet.dtomodel.MyListDto;
import si.feri.okusisvet.dtomodel.recipe.RecipeDto;
import si.feri.okusisvet.service.MyListService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("my-list")
public class MyListController {
    private final MyListService myListService;
    @Operation(description = "Create new list for recipes for user that made this request")
    @PostMapping
    public UUID createNewList(@RequestBody String title, HttpServletRequest request, HttpServletResponse response) {
        UUID id = myListService.createNewList(title, request);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return id;
    }

    @Operation(description = "Get all lists for user that made this request")
    @GetMapping("list")
    public List<MyListDto> getMyLists(HttpServletRequest request) {
        return myListService.getUsersLists(request);
    }

    @Operation(description = "Add recipe to list, user can only add recipes to his list and can only add public recipes except if recipes are his then they can be also private")
    @PostMapping("{listId}/{recipeId}")
    public void addRecipeToList(
            @PathVariable("listId") @Parameter(description = "Id of a lis to add recipe to") UUID listId,
            @PathVariable("recipeId") @Parameter(description = "Recipe id to add to list") UUID recipeId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        myListService.addRecipeToList(recipeId, listId, request);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Operation(description = "Get recipes (only basic info) from users recipe list, user can only get recipes from his list")
    @GetMapping("{listId}/recipes")
    public List<RecipeDto> getMyListRecipes(@PathVariable("listId") UUID listId, HttpServletRequest request) {
        return myListService.getMyListRecipes(listId, request);
    }

}
