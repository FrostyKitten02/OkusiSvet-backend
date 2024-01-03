package si.feri.okusisvet.repository.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.feri.okusisvet.model.Recipe;

import java.util.UUID;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, UUID>, CustomRecipeRepo {
}
