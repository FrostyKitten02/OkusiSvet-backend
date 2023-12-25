package si.feri.okusisvet.dtomodel.recipe;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListRecipesResponse {
    private List<RecipeOverviewDto> recipes;
}
