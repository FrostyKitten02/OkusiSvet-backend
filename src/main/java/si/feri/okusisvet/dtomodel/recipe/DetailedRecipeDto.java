package si.feri.okusisvet.dtomodel.recipe;

import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.RecipeState;

import java.util.List;

@Getter
@Setter
public class DetailedRecipeDto {
    private Integer version;
    private String title;
    private RecipeState state;
    private List<IngredientGroupDto> ingredientGroups;
    private List<RecipeStepDto> recipeSteps;
}
