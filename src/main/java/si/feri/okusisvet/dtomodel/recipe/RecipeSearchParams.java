package si.feri.okusisvet.dtomodel.recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeSearchParams {
    private String searchStr;
    private Boolean onlyUserRecipes;
}
