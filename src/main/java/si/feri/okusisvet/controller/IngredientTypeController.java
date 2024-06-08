package si.feri.okusisvet.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import si.feri.okusisvet.dtomodel.IngredientTypeDto;
import si.feri.okusisvet.dtomodel.IngredientTypeResponseDto;
import si.feri.okusisvet.dtomodel.NewIngredientType;
import si.feri.okusisvet.service.IngredientTypeService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("ingredient-types")
public class IngredientTypeController {
    private final IngredientTypeService ingredientTypeService;

    //TODO: needs to be replaced with search and return only top n results, when we have a lot of ingredient types this will be a problem
    //search is more usefull anyway, user will try searching fro ingredient type and if it is not found then he will add it
    @GetMapping
    public IngredientTypeResponseDto getAllIngredientTypes() {
        return ingredientTypeService.getAllIngredientTypes();
    }

    @PostMapping
    public UUID addIngredientType(@RequestBody NewIngredientType newIngredientType, HttpServletResponse response) {
        UUID uuid = ingredientTypeService.addIngredientType(newIngredientType);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return uuid;
    }

}
