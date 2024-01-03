package si.feri.okusisvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.feri.okusisvet.model.IngredientGroup;

import java.util.List;
import java.util.UUID;

@Repository
public interface IngredientGroupRepo extends JpaRepository<IngredientGroup, UUID> {
    List<IngredientGroup> findIngredientGroupByRecipeId(UUID recipeId);
}
