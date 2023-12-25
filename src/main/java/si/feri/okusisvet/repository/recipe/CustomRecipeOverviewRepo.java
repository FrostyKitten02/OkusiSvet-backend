package si.feri.okusisvet.repository.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import si.feri.okusisvet.model.Recipe;
import si.feri.okusisvet.model.RecipeOverview;

public interface CustomRecipeOverviewRepo {
    Page<RecipeOverview> findAllByCriteria(boolean onlyPublic, String searchStr, Pageable pageable);
}
