package si.feri.okusisvet.dtomodel.recipe;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import si.feri.okusisvet.dtomodel.SortInfoResponse;
import si.feri.okusisvet.mappers.recipe.RecipeMapper;
import si.feri.okusisvet.model.Recipe;
import si.feri.okusisvet.paging.PageInfo;
import si.feri.okusisvet.paging.RecipeSortInfo;

import java.util.List;

@Getter
@Setter
public class ListRecipeResponse {
    private List<RecipeDto> recipes;
    private PageInfo pageInfo;
    private SortInfoResponse sortInfo;
    public static ListRecipeResponse fromPage(Page<Recipe> page) {
        List<RecipeDto> recipes = page.getContent().stream().map(RecipeMapper.INSTANCE::toDto).toList();
        PageInfo pageInfoRes = PageInfo.from(page);

        RecipeSortInfo recipeSortInfo = RecipeSortInfo.fromPage(page);
        SortInfoResponse<RecipeSortInfo.Field> sortInfoRes;
        if (recipeSortInfo != null) {
             sortInfoRes = recipeSortInfo.toSortInfoResponse();
        } else {
            sortInfoRes = null;
        }

        ListRecipeResponse res = new ListRecipeResponse();
        res.setRecipes(recipes);
        res.setPageInfo(pageInfoRes);
        res.setSortInfo(sortInfoRes);
        return res;
    }
}
