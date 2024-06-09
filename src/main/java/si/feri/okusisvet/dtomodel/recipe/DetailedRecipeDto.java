package si.feri.okusisvet.dtomodel.recipe;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.RecipeDifficulty;
import si.feri.okusisvet.enums.RecipeState;
import si.feri.okusisvet.enums.RecipeType;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DetailedRecipeDto {
    private UUID id;
    private Integer version;
    private String title;
    private String ownerId;
    private RecipeState state;
    private RecipeType type;
    private RecipeDifficulty difficulty;
    private List<IngredientGroupDto> ingredientGroups;
    private List<RecipeStepDto> recipeSteps;
}
