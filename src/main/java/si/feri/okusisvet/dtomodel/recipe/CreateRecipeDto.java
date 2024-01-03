package si.feri.okusisvet.dtomodel.recipe;


import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.RecipeDifficulty;
import si.feri.okusisvet.enums.RecipeType;
import si.feri.okusisvet.enums.Unit;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateRecipeDto {
    private String title;
    private String comment;
    private RecipeDifficulty difficulty;
    private RecipeType type;
    private List<RecipeStep> steps;
    private List<RecipeIngredientGroup> ingredientGroups;

    public static record RecipeStep(
            @Min(1)
            Integer stepNumber,
            String title,
            String instructions
    ) { }
    public static record RecipeIngredientGroup(
            String name,
            @Min(1)
            Integer position,
            List<IngredientForRecipe> ingredients
    ) { }
    public static record IngredientForRecipe(String name,
                                             UUID id,
                                             Unit unit,
                                             @Min(1)
                                             Integer amount
    ) { }
}
