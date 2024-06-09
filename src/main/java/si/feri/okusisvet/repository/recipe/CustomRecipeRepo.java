package si.feri.okusisvet.repository.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import si.feri.okusisvet.model.Recipe;

public interface CustomRecipeRepo {
    Page<Recipe> findAllPublicByCriteria(String searchStr, String userId, Pageable pageable);
}
