package si.feri.okusisvet.repository.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.feri.okusisvet.model.RecipeStep;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecipeStepRepo extends JpaRepository<RecipeStep, UUID> {
    List<RecipeStep> findAllByRecipeId(UUID recipeId);
}
