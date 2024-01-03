package si.feri.okusisvet.dtomodel.recipe;


import lombok.Getter;
import lombok.Setter;
import si.feri.okusisvet.enums.Unit;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateRecipeDto {
    private String title;
    private List<RecipeIngredientGroup> ingredientGroups;
    private Boolean publish;
    //TODO add steps with descriptions!!

    public static record RecipeIngredientGroup(
            String name,
            Integer position,
            List<IngredientForRecipe> ingredients
    ) { }
    public static record IngredientForRecipe(String name,
                                             UUID id,
                                             Unit unit,
                                             Integer amount
    ) { }
}
